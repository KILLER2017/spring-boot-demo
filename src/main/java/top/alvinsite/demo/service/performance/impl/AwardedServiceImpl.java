package top.alvinsite.demo.service.performance.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.performance.AwardedDao;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.dto.performance.ResearcherPerformance;
import top.alvinsite.demo.model.entity.performance.Awarded;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.ScoreDistributionParam;
import top.alvinsite.demo.service.performance.AwardedService;
import top.alvinsite.demo.service.rule.AwardedRuleService;

import java.util.List;

@Service
public class AwardedServiceImpl extends AbstractPerformanceService<AwardedDao, Awarded> implements AwardedService {
    private final static String PERFORMANCE = "awarded";

    @Autowired
    private AwardedRuleService awardedRuleService;

    @Override
    protected String getPerformance() {
        return PERFORMANCE;
    }

    @Override
    protected List<ManagerUserDTO> getMembers(Awarded project) {
        return project.getAwardee();
    }

    @Override
    public Awarded calcProjectPoints(Awarded project) {
        if (!isUniformDepartment(project)) {
            project.setScore(0);
            return project;
        }

        float score = awardedRuleService.getScore(project);

        // 分值分配法
        score *= scoreDistributionService.getProportion(ScoreDistributionParam.build(project, PERFORMANCE));

        // 返回个人得分
        project.setScore(score);
        return project;
    }

    @Override
    protected List<Awarded> beforeFindAll(PerformanceQuery performanceQuery) {
        return baseMapper.findAwarded(performanceQuery);
    }

    @Override
    public void setTotalPoints(ResearcherPerformance researcherPerformance, float totalPoints) {
        researcherPerformance.setAwardedPoint(totalPoints);
    }

}
