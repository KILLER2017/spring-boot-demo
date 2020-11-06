package top.alvinsite.demo.controller.performance;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.model.dto.performance.ResearcherPerformance;
import top.alvinsite.demo.model.entity.performance.*;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.support.BaseResponse;
import top.alvinsite.demo.service.performance.*;
import top.alvinsite.utils.ExcelUtils;
import top.alvinsite.framework.spring.AsyncTaskManager;
import top.alvinsite.framework.spring.TaskInfo;
import top.alvinsite.framework.spring.TaskStatusEnum;
import top.alvinsite.framework.springsecurity.entity.User;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static top.alvinsite.utils.BeanUtils.updateProperties;

/**
 * @author Alvin
 */
@Slf4j
@RestController
@RequestMapping("performance/report")
public class ReportController {
    private final static String SUPER_USER_GROUP = "admin";

    @Value("${app.domain}")
    private String domain;

    @Autowired
    private LongitudinalProjectService longitudinalProjectService;

    @Autowired
    private CrossingProjectService crossingProjectService;

    @Autowired
    private PaperService paperService;

    @Autowired
    private LiteratureService literatureService;

    @Autowired
    private PatentService patentService;

    @Autowired
    private CopyrightService copyrightService;

    @Autowired
    private AwardedService awardedService;

    @Autowired
    private SummaryService summaryService;
    /**
     * 注入异步任务管理器
     */
    @Autowired
    AsyncTaskManager asyncTaskManager;

    protected void addManagerLimit(PerformanceQuery performanceQuery) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // 如果用户不是系统管理员，则限定只能查询自己管理机构的数据
        if (!SUPER_USER_GROUP.equals(user.getUserGroup()) && user.getManageUnitId() != null) {
            performanceQuery.setDepartmentId(user.getManageUnitId());
        }
    }

    /**
     * 创建报表生成任务
     * @param performanceQuery 过滤条件
     * @return 任务信息
     */
    @GetMapping("generate")
    public TaskInfo generate(PerformanceQuery performanceQuery) throws Exception {

        addManagerLimit(performanceQuery);

        return asyncTaskManager.submit(() -> {
            Workbook workbook = generateExcel(performanceQuery);
            return workbook;
        });
    }

    /**
     * 查询任务进度
     * @param taskId 任务ID
     * @return 任务信息
     */
    @GetMapping("query")
    public BaseResponse<TaskInfo> query(String taskId) {
        TaskInfo taskInfo = asyncTaskManager.getTaskInfo(taskId);

        Assert.notNull(taskInfo, "TaskId错误");

        if (taskInfo.getStatus() == TaskStatusEnum.SUCCESS) {
            TaskInfo result = new TaskInfo();
            updateProperties(taskInfo, result);
            result.setResult(String.format("http://%s/performance/export/result?taskId=%s", domain, taskId));

            return BaseResponse.ok("导出文件生成完毕", result);
        }

        return BaseResponse.ok("正在生成导出文件", taskInfo);
    }

    /**
     * 下载生成的报表
     * @param taskId 任务ID
     * @param response 响应报文
     */
    @GetMapping("download")
    public BaseResponse<TaskInfo> download(String taskId, HttpServletResponse response) {
        TaskInfo taskInfo = asyncTaskManager.getTaskInfo(taskId);

        Assert.notNull(taskInfo, "TaskId错误");

        if (taskInfo.getStatus() == TaskStatusEnum.SUCCESS) {
            Workbook workbook = (Workbook) taskInfo.getResult();
            ExcelUtils.buildExcelDocument("科研绩效.xlsx", workbook, response);
        }

        return BaseResponse.ok("正在生成导出文件", taskInfo);
    }

    private Workbook generateExcel(PerformanceQuery performanceQuery) {
        // 汇总
        List<ResearcherPerformance> researcherPerformanceList = summaryService.findAll(performanceQuery);
        // 纵向项目
        List<LongitudinalProject> longitudinalProjectList = longitudinalProjectService.findAll(performanceQuery);
        // 横向项目
        List<CrossingProject> crossingProjectList = crossingProjectService.findAll(performanceQuery);
        // 论文成果
        List<Paper> paperList = paperService.findAll(performanceQuery);
        // 著作成果
        List<Literature> literatureList = literatureService.findAll(performanceQuery);
        // 专利成果
        List<Patent> patentList = patentService.findAll(performanceQuery);
        // 著作权
        List<Copyright> copyrightList = copyrightService.findAll(performanceQuery);
        // 科研获奖
        List<Awarded> awardedList = awardedService.findAll(performanceQuery);

        return new ExcelUtils.Builder()
                .addSheet("汇总", researcherPerformanceList, ResearcherPerformance.class)
                .addSheet("纵向项目", longitudinalProjectList, LongitudinalProject.class)
                .addSheet("横向项目", crossingProjectList, CrossingProject.class)
                .addSheet("论文成果", paperList, Paper.class)
                .addSheet("著作成果", literatureList, Literature.class)
                .addSheet("专利成果", patentList, Patent.class)
                .addSheet("著作权", copyrightList, Copyright.class)
                .addSheet("科研获奖", awardedList, Awarded.class)
                .build();
    }
}
