package top.alvinsite.demo.service.performance.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.performance.LongitudinalProjectDao;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.dto.performance.ResearcherPerformance;
import top.alvinsite.demo.model.entity.performance.LongitudinalProject;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.ScoreDistributionParam;
import top.alvinsite.demo.service.performance.LongitudinalProjectService;
import top.alvinsite.demo.service.rule.LongitudinalRuleService;
import top.alvinsite.utils.TimeUtils;

import java.util.List;

@Service
public class LongitudinalProjectServiceImpl extends AbstractPerformanceService<LongitudinalProjectDao, LongitudinalProject> implements LongitudinalProjectService {

    private final static String PERFORMANCE = "longitudinal";

    @Autowired
    private LongitudinalRuleService longitudinalRuleService;

    @Override
    protected String getPerformance() {
        return PERFORMANCE;
    }

    @Override
    protected List<ManagerUserDTO> getMembers(LongitudinalProject project) {
        return project.getMembers();
    }

    @Override
    public LongitudinalProject calcProjectPoints(LongitudinalProject project) {
        float score = longitudinalRuleService.getScore(project);

        float proportion = scoreDistributionService.getProportion(ScoreDistributionParam.build(project, PERFORMANCE));
        // 项目年度数
        int annualNum = TimeUtils.getAnnualNum(project.getStartedTime(), project.getFinishedTime());

        // 返回个人得分
        project.setBudgetScore(project.getBudgetScore() * proportion / annualNum);
        project.setProjectScore(project.getProjectScore() * proportion / annualNum);
        project.setScore(score * proportion / annualNum);
        return project;
    }


    @Override
    protected List<LongitudinalProject> beforeFindAll(PerformanceQuery performanceQuery) {
        return baseMapper.findAll(performanceQuery);
    }

    @Override
    public void setTotalPoints(ResearcherPerformance researcherPerformance, float totalPoints) {
        researcherPerformance.setLongitudinalPoint(totalPoints);
    }

}
