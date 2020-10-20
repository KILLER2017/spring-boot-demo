package top.alvinsite.demo.service.impl.performance;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.ResearcherDao;
import top.alvinsite.demo.dao.performance.*;
import top.alvinsite.demo.model.dto.performance.ResearcherPerformance;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.TotalPointsParam;
import top.alvinsite.demo.service.performance.*;
import top.alvinsite.demo.service.rule.LiteratureRuleService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SummaryServiceImpl extends ServiceImpl<ResearcherDao, ResearcherPerformance> implements SummaryService {

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private PaperDao paperDao;

    @Autowired
    private PatentDao patentDao;

    @Autowired
    private CopyrightDao copyrightDao;

    @Autowired
    private AwardedDAO awardedDAO;

    @Autowired
    private AwardedService awardedService;

    @Autowired
    private CopyrightService copyrightService;

    @Autowired
    private CrossingProjectService crossingProjectService;

    @Autowired
    private LiteratureService literatureService;

    @Autowired
    private LongitudinalProjectService longitudinalProjectService;

    @Autowired
    private PaperService paperService;

    @Autowired
    private PatentService patentService;

    @Override
    public List<ResearcherPerformance> findAll(PerformanceQuery performanceQuery) {
        List<ResearcherPerformance> list = baseMapper.findAll(performanceQuery);
        list.stream()
                .map(awardedService::calcTotalPoints)
                .map(copyrightService::calcTotalPoints)
                .map(crossingProjectService::calcTotalPoints)
                .map(literatureService::calcTotalPoints)
                .map(longitudinalProjectService::calcTotalPoints)
                .map(paperService::calcTotalPoints)
                .map(patentService::calcTotalPoints)
                .map(this::calcTotalPoints)
                .collect(Collectors.toList());

        return list;
    }

    @Override
    public ResearcherPerformance calcTotalPoints(ResearcherPerformance researcherPerformance) {
        float totalPoints = researcherPerformance.getAwardedPoint()
                + researcherPerformance.getCopyrightPoint()
                + researcherPerformance.getCrossingPoint()
                + researcherPerformance.getLiteraturePoint()
                + researcherPerformance.getLongitudinalPoint()
                + researcherPerformance.getPaperPoint()
                + researcherPerformance.getPatentPoint();
        researcherPerformance.setTotalPoints(totalPoints);
        return researcherPerformance;
    }

    @Override
    public void setTotalPoints(ResearcherPerformance researcherPerformance, float totalPoints) {
        researcherPerformance.setTotalPoints(totalPoints);
    }

}
