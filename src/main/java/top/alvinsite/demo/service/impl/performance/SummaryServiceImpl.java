package top.alvinsite.demo.service.impl.performance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.ResearcherDao;
import top.alvinsite.demo.dao.performance.*;
import top.alvinsite.demo.model.dto.performance.ResearcherPerformance;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.TotalPointsParam;
import top.alvinsite.demo.service.performance.SummaryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SummaryServiceImpl implements SummaryService {
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
    private ResearcherDao researcherDao;

    @Override
    public List<ResearcherPerformance> findAll(PerformanceQuery performanceQuery) {
        List<ResearcherPerformance> list = researcherDao.findAll(performanceQuery);

        list.stream().map(researcherPerformance -> calcTotalPoints(researcherPerformance, performanceQuery.getYear())).collect(Collectors.toList());
        return list;
    }

    /**
     * 计算总绩效
     * @param researcherPerformance
     * @param year
     * @return
     */
    private ResearcherPerformance calcTotalPoints(ResearcherPerformance researcherPerformance, int year) {
        TotalPointsParam totalPointsParam = new TotalPointsParam(researcherPerformance.getAccount(), year);

        Float longitudinalPoint = projectDao.calcLongitudinalTotalPoints(totalPointsParam);
        Float crossingPoint = projectDao.calcCrossingTotalPoints(totalPointsParam);
        Float paperPoint = paperDao.calcPaperTotalPoints(totalPointsParam);
        Float literaturePoint = paperDao.calcPaperTotalPoints(totalPointsParam);
        Float patentPoint = patentDao.calcPatentTotalPoints(totalPointsParam);
        Float copyrightPoint = copyrightDao.calcCopyrightTotalPoints(totalPointsParam);
        Float awardedPoint = awardedDAO.calcAwardedTotalPoints(totalPointsParam);

        longitudinalPoint = longitudinalPoint == null ? 0 : longitudinalPoint;
        crossingPoint = crossingPoint == null ? 0 : crossingPoint;
        paperPoint = paperPoint == null ? 0 : paperPoint;
        literaturePoint = literaturePoint == null ? 0 : literaturePoint;
        patentPoint = patentPoint == null ? 0 : patentPoint;
        copyrightPoint = copyrightPoint == null ? 0 : copyrightPoint;
        awardedPoint = awardedPoint == null ? 0 : awardedPoint;

        float totalPoints = 0;
        totalPoints += longitudinalPoint;
        totalPoints += crossingPoint;
        totalPoints += paperPoint;
        totalPoints += literaturePoint;
        totalPoints += patentPoint;
        totalPoints += copyrightPoint;
        totalPoints += awardedPoint;

        researcherPerformance.setTotalPoints(totalPoints);
        researcherPerformance.setLongitudinalPoint(longitudinalPoint);
        researcherPerformance.setCrossingPoint(crossingPoint);
        researcherPerformance.setPaperPoint(paperPoint);
        researcherPerformance.setLiteraturePoint(literaturePoint);
        researcherPerformance.setPatentPoint(patentPoint);
        researcherPerformance.setCopyrightPoint(copyrightPoint);
        researcherPerformance.setAwardedPoint(awardedPoint);
        return researcherPerformance;
    }
}
