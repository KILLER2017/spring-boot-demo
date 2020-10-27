package top.alvinsite.demo.controller.salary;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.jexl3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.alvinsite.demo.dao.salary.LevelFactorDao;
import top.alvinsite.demo.dao.salary.RuleDao;
import top.alvinsite.demo.dao.salary.SalarySummaryDao;
import top.alvinsite.demo.dao.salary.WorkloadTargetDao;
import top.alvinsite.demo.model.entity.salary.LevelFactor;
import top.alvinsite.demo.model.entity.salary.Rule;
import top.alvinsite.demo.model.entity.salary.SalarySummary;
import top.alvinsite.demo.model.entity.salary.WorkloadTarget;
import top.alvinsite.demo.model.params.*;
import top.alvinsite.demo.model.support.UserInfo;
import top.alvinsite.demo.utils.ExcelUtils;
import xcz.annotation.PermissionClass;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Administrator
 */
@Slf4j
@RestController
@RequestMapping("salary/summaries")
@PermissionClass
public class SummaryController {

    private final static String SUPER_USER_GROUP = "admin";

    @Autowired
    private RuleDao ruleDao;

    @Autowired
    private LevelFactorDao levelFactorDao;

    @Autowired
    private WorkloadTargetDao workloadTargetDao;

    @Autowired
    private SalarySummaryDao salarySummaryDao;

    @GetMapping
    public PageInfo<SalarySummary> list(@RequestHeader("authorization") UserInfo userInfo, Page page, PerformanceQuery performanceQuery) {

        // 如果用户不是系统管理员，则限定只能查询自己管理机构的数据
        if (!SUPER_USER_GROUP.equals(userInfo.getUserGroup()) && userInfo.getManageUnits() != null) {
            performanceQuery.setDepartmentScope(userInfo.getManageUnits());
        }

        PageHelper.startPage(page.getPageNum(), page.getPageSize());

        List<SalarySummary> list = salarySummaryDao.findAll(performanceQuery);

        list.stream().map(salarySummary -> calcTotalSalary(salarySummary, performanceQuery.getYear())).collect(Collectors.toList());
        return new PageInfo<>(list);
    }

    private SalarySummary calcTotalSalary(SalarySummary salarySummary, Integer year) {
        if (salarySummary.getLevel() == null ||
                salarySummary.getPostType() == null
        ) {
            log.info("职务级别或类型岗位为空，跳过绩效工资计算：" + salarySummary);
            return salarySummary;
        }

        JexlContext jexlContext = new MapContext();

        // 激励绩效工资
        jexlContext.set("a", salarySummary.getIncentiveWage());
        // 超课时津贴
        jexlContext.set("b", salarySummary.getOvertimeSubsidy());
        // 岗位津贴
        jexlContext.set("c", salarySummary.getJobSubsidy());
        // 实际教学工作量
        jexlContext.set("g", salarySummary.getTeachingWorkload());
        // 实际科研工作量
        jexlContext.set("h", salarySummary.getResearchWorkload());

        LevelFactorParam levelFactorParam = new LevelFactorParam(salarySummary.getType(), salarySummary.getLevel());
        LevelFactor levelFactor = levelFactorDao.findOneByTypeAndLevel(levelFactorParam);
        Assert.notNull(levelFactor, "对应级差系数不能为空：" + levelFactorParam);
        // 级差系数
        jexlContext.set("d", levelFactor.getFactor());

        WorkloadTargetParam workloadTargetParam = new WorkloadTargetParam(salarySummary.getLevel(), salarySummary.getPostType());
        WorkloadTarget workloadTarget = workloadTargetDao.findOneByLevelAndPostType(workloadTargetParam);

        Assert.notNull(workloadTarget, "对应目标工作量不能为空：" + workloadTargetParam);
        // 年度目标教学工作量
        jexlContext.set("e", workloadTarget.getTeachingWorkloadTarget());
        // 年度目标科研工作量
        jexlContext.set("f", workloadTarget.getResearchWorkloadTarget());
        // 个人实际民主测评值
        jexlContext.set("i", salarySummary.getMeasurement());

        Rule rule = ruleDao.findOne(new SalaryRuleParam(year, salarySummary.getPost(), salarySummary.getPostType()));


        Assert.notNull(rule, String.format("对应计算规则不能为空：规则（年份：%n，系列岗位：%s，岗位类型：%s）", year, salarySummary.getPost(), salarySummary.getPostType()));

        JexlEngine jexlEngine = new JexlBuilder().create();
        JexlExpression jexlExpression = jexlEngine.createExpression(rule.getRule());
        salarySummary.setPerformanceWage((double)jexlExpression.evaluate(jexlContext));

        double totalSalary = salarySummary.getPerformanceWage()
                + salarySummary.getIncentiveWage()
                + salarySummary.getOvertimeSubsidy()
                + salarySummary.getJobSubsidy()
                + salarySummary.getSpecialSubsidy();
        salarySummary.setTotalSalary(totalSalary);
        return salarySummary;
    }

    @PostMapping("importExcel")
    public void importExcel(Integer year, @RequestParam(value="uploadFile") MultipartFile file) {
        long t1 = System.currentTimeMillis();
        List<SalarySummary> list = ExcelUtils.readExcel("", SalarySummary.class, file);
        long t2 = System.currentTimeMillis();
        System.out.println(String.format("read over! cost:%sms", String.valueOf(t2 - t1)));

        list.forEach(
            item -> {
                PerformanceQuery performanceQuery = new PerformanceQuery();
                performanceQuery.setAccount(item.getAccount());
                performanceQuery.setYear(year);
                SalarySummary salarySummary = salarySummaryDao.findOneByAccountAndYear(performanceQuery);

                if (salarySummary != null) {
                    item.setId(salarySummary.getId());
                }
                item.setYear(year);
            }
        );

        salarySummaryDao.saveBatch(list);
    }

    @GetMapping("exportExcel")
    public void exportExcel(@RequestParam("authorization") UserInfo userInfo, PerformanceQuery performanceQuery, HttpServletResponse response) {
        // 如果用户不是系统管理员，则限定只能查询自己管理机构的数据
        if (!SUPER_USER_GROUP.equals(userInfo.getUserGroup()) && userInfo.getManageUnits() != null) {
            performanceQuery.setDepartmentScope(userInfo.getManageUnits());
        }

        List<SalarySummary> list = salarySummaryDao.findAll(performanceQuery);
        ExcelUtils.writeExcel(response, list, SalarySummary.class);
    }
}
