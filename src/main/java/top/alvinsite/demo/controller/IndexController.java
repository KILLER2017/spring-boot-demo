package top.alvinsite.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import top.alvinsite.demo.dao.ResearcherDao;
import top.alvinsite.demo.dao.performance.*;
import top.alvinsite.demo.model.dto.performance.*;
import top.alvinsite.demo.model.entity.performance.*;
import top.alvinsite.demo.model.params.Page;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.TotalPointsParam;
import top.alvinsite.demo.model.support.BaseResponse;
import top.alvinsite.demo.model.support.UserInfo;
import top.alvinsite.demo.service.performance.*;
import top.alvinsite.demo.utils.ExcelUtils;
import top.alvinsite.framework.spring.AsyncTaskManager;
import top.alvinsite.framework.spring.TaskInfo;
import top.alvinsite.framework.spring.TaskStatusEnum;
import xcz.annotation.IgnorePermission;
import xcz.annotation.PermissionClass;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

import static top.alvinsite.demo.utils.BeanUtils.updateProperties;

@Slf4j
@RestController
@RequestMapping("performance/export")
@PermissionClass
public class IndexController {

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

    //注入异步任务管理器
    @Autowired
    AsyncTaskManager asyncTaskManager;

    protected void addManagerLimit(UserInfo userInfo, PerformanceQuery performanceQuery) {
        // 如果用户不是系统管理员，则限定只能查询自己管理机构的数据
        if (userInfo.getUserGroup() != "admin" && userInfo.getManageUnitId() != null) {
            performanceQuery.setDepartmentId(userInfo.getManageUnitId());
        }
    }


    @GetMapping
    public void exportExcel(@RequestParam("authorization") UserInfo userInfo, Page page, PerformanceQuery performanceQuery, HttpServletResponse response) {
        addManagerLimit(userInfo, performanceQuery);
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

        new ExcelUtils.Builder()
                .addSheet("汇总", researcherPerformanceList, ResearcherPerformance.class)
                .addSheet("纵向项目", longitudinalProjectList, LongitudinalProject.class)
                .addSheet("横向项目", crossingProjectList, CrossingProject.class)
                .addSheet("论文成果", paperList, Paper.class)
                .addSheet("著作成果", literatureList, Literature.class)
                .addSheet("专利成果", patentList, Patent.class)
                .addSheet("著作权", copyrightList, Copyright.class)
                .addSheet("科研获奖", awardedList, Awarded.class)
                .setExcelName("科研绩效.xlsx")
                .setResponse(response)
                .build();
    }

    @GetMapping("async")
    @IgnorePermission
    public TaskInfo asyncExportExcel(PerformanceQuery performanceQuery) throws Exception {
        String filename = "file.xlsx";
        TaskInfo taskInfo = asyncTaskManager.submit(() -> {
            Workbook workbook = generateExcel(performanceQuery, filename);
            return workbook;
        });
        return taskInfo;
    }

    @GetMapping("query")
    @IgnorePermission
    public BaseResponse<TaskInfo> queryTaskInfo(String taskId) {
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

    @GetMapping("result")
    @IgnorePermission
    public BaseResponse<TaskInfo> asyncExportExcel(String taskId, HttpServletResponse response) {
        TaskInfo taskInfo = asyncTaskManager.getTaskInfo(taskId);

        Assert.notNull(taskInfo, "TaskId错误");

        if (taskInfo.getStatus() == TaskStatusEnum.SUCCESS) {
            Workbook workbook = (Workbook) taskInfo.getResult();
            ExcelUtils.buildExcelDocument("科研绩效.xlsx", workbook, response);
        }

        return BaseResponse.ok("正在生成导出文件", taskInfo);
    }



    private Workbook generateExcel(PerformanceQuery performanceQuery, String filename) {
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

        Workbook workbook = new ExcelUtils.Builder()
                .addSheet("汇总", researcherPerformanceList, ResearcherPerformance.class)
                .addSheet("纵向项目", longitudinalProjectList, LongitudinalProject.class)
                .addSheet("横向项目", crossingProjectList, CrossingProject.class)
                .addSheet("论文成果", paperList, Paper.class)
                .addSheet("著作成果", literatureList, Literature.class)
                .addSheet("专利成果", patentList, Patent.class)
                .addSheet("著作权", copyrightList, Copyright.class)
                .addSheet("科研获奖", awardedList, Awarded.class)
                .build();

        return workbook;
    }

}
