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
import top.alvinsite.demo.model.entity.performance.CrossingProject;
import top.alvinsite.demo.model.entity.performance.LongitudinalProject;
import top.alvinsite.demo.model.params.Page;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.TotalPointsParam;
import top.alvinsite.demo.model.support.UserInfo;
import top.alvinsite.demo.service.performance.SummaryService;
import top.alvinsite.demo.utils.ExcelUtils;
import xcz.annotation.IgnorePermission;
import xcz.annotation.PermissionClass;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("performance")
@PermissionClass
public class IndexController extends BaseController {
    /**
     * 项目
     */
    @Autowired
    private ProjectDao projectDao;

    /**
     * 论文
     */
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


    @GetMapping("summaries")
    public PageInfo<ResearcherPerformance> summaries(@RequestHeader("authorization") UserInfo userInfo, Page page, PerformanceQuery performanceQuery) {
        addManagerLimit(userInfo, performanceQuery);
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<ResearcherPerformance> list = summaryService.findAll(performanceQuery);
        return new PageInfo<>(list);
    }






    @GetMapping("paper")
    public PageInfo<PaperDTO> findPaper(@RequestHeader("authorization") UserInfo userInfo, Page page, PerformanceQuery performanceQuery) {
        addManagerLimit(userInfo, performanceQuery);

        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<PaperDTO> list = paperDao.findPaper(performanceQuery);

        return new PageInfo<>(list);
    }

    @GetMapping("literature")
    public PageInfo<LiteratureDTO> findLiterature(@RequestHeader("authorization") UserInfo userInfo, Page page, PerformanceQuery performanceQuery) {
        addManagerLimit(userInfo, performanceQuery);

        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<LiteratureDTO> list = literatureDao.findLiterature(performanceQuery);
        return new PageInfo<>(list);
    }

    @GetMapping("patent")
    public PageInfo<PatentDTO> findPatent(@RequestHeader("authorization") UserInfo userInfo, Page page, PerformanceQuery performanceQuery) {
        addManagerLimit(userInfo, performanceQuery);

        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<PatentDTO> list = patentDao.findPatent(performanceQuery);
        return new PageInfo<>(list);
    }

    @GetMapping("copyright")
    public PageInfo<CopyrightDTO> findCopyright(@RequestHeader("authorization") UserInfo userInfo, Page page, PerformanceQuery performanceQuery) {
        addManagerLimit(userInfo, performanceQuery);

        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<CopyrightDTO> list = copyrightDao.findCopyright(performanceQuery);
        return new PageInfo<>(list);
    }

    @GetMapping("awarded")
    public PageInfo<AwardedDTO> findAwarded(@RequestHeader("authorization") UserInfo userInfo, Page page, PerformanceQuery performanceQuery) {
        addManagerLimit(userInfo, performanceQuery);

        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<AwardedDTO> list = awardedDAO.findAwarded(performanceQuery);
        return new PageInfo<>(list);
    }

    @GetMapping("export-excel")
    public void exportExcel(@RequestHeader("authorization") UserInfo userInfo, Page page, PerformanceQuery performanceQuery, HttpServletResponse response) {
        addManagerLimit(userInfo, performanceQuery);
        // 汇总
        List<ResearcherPerformance> researcherPerformanceList = summaryService.findAll(performanceQuery);
        // 纵向项目
        List<LongitudinalProject> longitudinalProjectList = projectDao.findLongitudinalProject(performanceQuery);
        // 横向项目
        List<CrossingProject> crossingProjectList = projectDao.findCrossingProject(performanceQuery);
        // 论文成果
        List<PaperDTO> paperDTOList = paperDao.findPaper(performanceQuery);
        // 著作成果
        List<LiteratureDTO> literatureDTOList = literatureDao.findLiterature(performanceQuery);
        // 专利成果
        List<PatentDTO> patentDTOList = patentDao.findPatent(performanceQuery);
        // 著作权
        List<CopyrightDTO> copyrightDTOList = copyrightDao.findCopyright(performanceQuery);
        // 科研获奖
        List<AwardedDTO> awardedDTOList = awardedDAO.findAwarded(performanceQuery);

        new ExcelUtils.Builder()
                .addSheet("汇总", researcherPerformanceList, ResearcherPerformance.class)
                .addSheet("纵向项目", longitudinalProjectList, LongitudinalProject.class)
                .addSheet("横向项目", crossingProjectList, CrossingProject.class)
                .addSheet("论文成果", paperDTOList, PaperDTO.class)
                .addSheet("著作成果", literatureDTOList, LiteratureDTO.class)
                .addSheet("专利成果", patentDTOList, PatentDTO.class)
                .addSheet("著作权", copyrightDTOList, CopyrightDTO.class)
                .addSheet("科研获奖", awardedDTOList, AwardedDTO.class)
                .setResponse(response)
                .build();
    }

}
