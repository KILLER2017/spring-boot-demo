package top.alvinsite.demo.controller.salary;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.jexl3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.alvinsite.demo.dao.salary.LevelFactorDao;
import top.alvinsite.demo.dao.salary.RuleDao;
import top.alvinsite.demo.dao.salary.SalarySummaryDao;
import top.alvinsite.demo.dao.salary.WorkloadTargetDao;
import top.alvinsite.exception.MyJexlExpression;
import top.alvinsite.demo.model.entity.salary.LevelFactor;
import top.alvinsite.demo.model.entity.salary.Rule;
import top.alvinsite.demo.model.entity.salary.SalarySummary;
import top.alvinsite.demo.model.entity.salary.WorkloadTarget;
import top.alvinsite.demo.model.params.*;
import top.alvinsite.utils.ExcelUtils;
import top.alvinsite.framework.springsecurity.entity.User;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

import static top.alvinsite.utils.BeanUtils.transformFrom;

/**
 * @author Administrator
 */
@Slf4j
@RestController
@RequestMapping("salary/summaries")
public class SummaryController {

    @Autowired
    private RuleDao ruleDao;

    @Autowired
    private LevelFactorDao levelFactorDao;

    @Autowired
    private WorkloadTargetDao workloadTargetDao;

    @Autowired
    private SalarySummaryDao salarySummaryDao;

    /**
     * 列表
     * @param user
     * @param page
     * @param performanceQuery
     * @return
     */
    @GetMapping
    public PageInfo<SalarySummary> list(@AuthenticationPrincipal User user, Page page, PerformanceQuery performanceQuery) {

        PageHelper.startPage(page.getPageNum(), page.getPageSize());

        List<SalarySummary> list = salarySummaryDao.findAll(performanceQuery);

        list.stream()
                .map(salarySummary -> calcTotalSalary(salarySummary, performanceQuery.getYear()))
                .collect(Collectors.toList());
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

        LevelFactorParam levelFactorParam = transformFrom(salarySummary, LevelFactorParam.class);
        LevelFactor levelFactor = levelFactorDao.findOneByTypeAndLevel(levelFactorParam);

        if (levelFactor == null) {
            log.error("对应级差系数不能为空：{}", levelFactorParam);
            return salarySummary;
        }

        // 级差系数
        jexlContext.set("d", levelFactor.getFactor());

        WorkloadTargetParam workloadTargetParam = transformFrom(salarySummary, WorkloadTargetParam.class);
        WorkloadTarget workloadTarget = workloadTargetDao.findOneByLevelAndPostType(workloadTargetParam);

        if (workloadTarget == null) {
            log.error("对应目标工作量不能为空：{}", workloadTargetParam);
            return salarySummary;
        }

        // 年度目标教学工作量
        jexlContext.set("e", workloadTarget.getTeachingWorkloadTarget());
        // 年度目标科研工作量
        jexlContext.set("f", workloadTarget.getResearchWorkloadTarget());
        // 个人实际民主测评值
        jexlContext.set("i", salarySummary.getMeasurement());
        // 实际完成教研教改工作量
        jexlContext.set("j", salarySummary.getTeachingResearchWorkload());
        // 实际完成总实验教学工作量
        jexlContext.set("k", salarySummary.getExperimentalTeachingWorkload());
        // 年度目标实验教学工作量
        jexlContext.set("l", workloadTarget.getExperimentalTeachingWorkloadTarget());

        SalaryRuleParam salaryRuleParam = transformFrom(salarySummary, SalaryRuleParam.class);
        assert salaryRuleParam != null;
        salaryRuleParam.setYear(year);
        Rule rule = ruleDao.findOne(salaryRuleParam);

        if (rule == null) {
            log.error("对应计算规则不能为空：规则（年份：{}，系列岗位：{}，岗位类型：{}）", year, salarySummary.getPost(), salarySummary.getPostType());
            return salarySummary;
        }

        JexlEngine jexlEngine = new JexlBuilder().create();
        try {
            JexlExpression jexlExpression = jexlEngine.createExpression(rule.getRule());
            salarySummary.setPerformanceWage((double)jexlExpression.evaluate(jexlContext));
        } catch (JexlException e) {

            String errorMessage = String.format("%s年，%s系列岗位，%s岗位类型的规则（%s）不合法", year, salarySummary.getPost(), salarySummary.getPostType(), rule.getRule());
            throw new MyJexlExpression(errorMessage);
        }

        double totalSalary = salarySummary.getPerformanceWage()
                + salarySummary.getIncentiveWage()
                + salarySummary.getOvertimeSubsidy()
                + salarySummary.getJobSubsidy()
                + salarySummary.getSpecialSubsidy();
        salarySummary.setTotalSalary(totalSalary);
        return salarySummary;
    }

    /**
     * 导入
     * @param year
     * @param file
     */
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

    /**
     * 导出
     * @param performanceQuery 查询过滤参数
     * @param response 请求响应
     */
    @GetMapping("exportExcel")
    public void exportExcel(PerformanceQuery performanceQuery, HttpServletResponse response) {
        List<SalarySummary> list = salarySummaryDao.findAll(performanceQuery);
        ExcelUtils.writeExcel(response, list, SalarySummary.class);
    }

    /**
     * 更新用户岗位职责完成情况
     */
    public void updateJobDetail() {

    }
}
