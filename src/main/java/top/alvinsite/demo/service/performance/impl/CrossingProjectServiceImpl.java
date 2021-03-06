package top.alvinsite.demo.service.performance.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.performance.CrossingProjectDao;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.dto.performance.ResearcherPerformance;
import top.alvinsite.demo.model.entity.performance.CrossingProject;
import top.alvinsite.demo.model.param.PerformanceQuery;
import top.alvinsite.demo.model.param.ScoreDistributionParam;
import top.alvinsite.demo.service.performance.CrossingProjectService;
import top.alvinsite.demo.service.rule.CrossingRuleService;
import top.alvinsite.util.TimeUtils;

import java.util.List;

@Service
public class CrossingProjectServiceImpl extends AbstractPerformanceService<CrossingProjectDao, CrossingProject> implements CrossingProjectService {

    private final static String PERFORMANCE = "crossing";

    @Autowired
    private CrossingRuleService crossingRuleService;

    @Override
    protected String getPerformance() {
        return PERFORMANCE;
    }

    @Override
    protected List<ManagerUserDTO> getMembers(CrossingProject project) {
        return project.getMembers();
    }

    @Override
    public CrossingProject calcProjectPoints(CrossingProject project) {
        String userDepartment = project.getDepartment().getId();
        String firstMemberDepartment = getMembers(project).get(0).getDepartment();
        if (!userDepartment.equals(firstMemberDepartment)) {
            project.setBudgetScore(0);
            project.setProjectScore(0);
            project.setScore(0);
            return project;
        }

        // 读取计分规则
        double score = crossingRuleService.getScore(project);

        float proportion = scoreDistributionService.getProportion(ScoreDistributionParam.build(project, PERFORMANCE));
        int annualNum = TimeUtils.getAnnualNum(project.getStartedTime(), project.getStartedTime());
        // 返回个人得分
        project.setBudgetScore(project.getBudgetScore() * proportion / annualNum);
        project.setProjectScore(project.getProjectScore() * proportion / annualNum);
        project.setScore(score * proportion / annualNum);
        return project;
    }

    @Override
    protected List<CrossingProject> beforeFindAll(PerformanceQuery performanceQuery) {
        return baseMapper.findAll(performanceQuery);
    }

    @Override
    public void setTotalPoints(ResearcherPerformance researcherPerformance, float totalPoints) {
        researcherPerformance.setCrossingPoint(totalPoints);
    }
}
