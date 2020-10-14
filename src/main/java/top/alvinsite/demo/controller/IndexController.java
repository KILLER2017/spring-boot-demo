package top.alvinsite.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.dao.ResearcherDao;
import top.alvinsite.demo.dao.performance.*;
import top.alvinsite.demo.model.dto.performance.*;
import top.alvinsite.demo.model.entity.performance.*;
import top.alvinsite.demo.model.params.Page;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.TotalPointsParam;
import top.alvinsite.demo.model.support.UserInfo;
import top.alvinsite.demo.service.performance.PaperService;
import top.alvinsite.demo.service.performance.SummaryService;
import top.alvinsite.demo.utils.ExcelUtils;
import xcz.annotation.IgnorePermission;
import xcz.annotation.PermissionClass;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("performance-old")
@PermissionClass
public class IndexController {



    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private PaperDao paperDao;

    @Autowired
    private LiteratureDao literatureDao;

    @Autowired
    private PatentDao patentDao;
    @Autowired
    private CopyrightDao copyrightDao;
    @Autowired
    private AwardedDAO awardedDAO;

    @Autowired
    private SummaryService summaryService;

    @GetMapping
    @IgnorePermission
    public String index() {
        return "this is index page!";
    }

    protected void addManagerLimit(UserInfo userInfo, PerformanceQuery performanceQuery) {
        // 如果用户不是系统管理员，则限定只能查询自己管理机构的数据
        if (userInfo.getUserGroup() != "admin" && userInfo.getManageUnitId() != null) {
            performanceQuery.setDepartmentId(userInfo.getManageUnitId());
        }
    }

    @GetMapping("summaries")
    public PageInfo<ResearcherPerformance> summaries(@RequestHeader("authorization") UserInfo userInfo, Page page, PerformanceQuery performanceQuery) {
        addManagerLimit(userInfo, performanceQuery);
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<ResearcherPerformance> list = summaryService.findAll(performanceQuery);
        return new PageInfo<>(list);
    }


    @GetMapping("export-excel")
    public void exportExcel(@RequestHeader("authorization") UserInfo userInfo, Page page, PerformanceQuery performanceQuery, HttpServletResponse response) {
        addManagerLimit(userInfo, performanceQuery);
        // 汇总
        // List<ResearcherPerformance> researcherPerformanceList = summaryService.findAll(performanceQuery);
        // 纵向项目
        // List<LongitudinalProject> longitudinalProjectList = projectDao.findLongitudinalProject(performanceQuery);
        // 横向项目
        // List<CrossingProject> crossingProjectList = projectDao.findCrossingProject(performanceQuery);
        // 论文成果
        // List<Paper> paperList = paperDao.findPaper(performanceQuery);
        // 著作成果
        List<Literature> literatureList = literatureDao.findLiterature(performanceQuery);
        // 专利成果
        // List<Patent> patentList = patentDao.findPatent(performanceQuery);
        // 著作权
        // List<Copyright> copyrightList = copyrightDao.findCopyright(performanceQuery);
        // 科研获奖
        // List<Awarded> awardedList = awardedDAO.findAwarded(performanceQuery);

        new ExcelUtils.Builder()
                // .addSheet("汇总", researcherPerformanceList, ResearcherPerformance.class)
                // .addSheet("纵向项目", longitudinalProjectList, LongitudinalProject.class)
                // .addSheet("横向项目", crossingProjectList, CrossingProject.class)
                // .addSheet("论文成果", paperList, Paper.class)
                .addSheet("著作成果", literatureList, Literature.class)
                // .addSheet("专利成果", patentList, Patent.class)
                // .addSheet("著作权", copyrightList, Copyright.class)
                // .addSheet("科研获奖", awardedList, Awarded.class)
                .setResponse(response)
                .build();
    }

}
