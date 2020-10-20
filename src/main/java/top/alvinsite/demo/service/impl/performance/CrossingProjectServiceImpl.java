package top.alvinsite.demo.service.impl.performance;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.ProjectMemberDao;
import top.alvinsite.demo.dao.performance.ProjectDao;
import top.alvinsite.demo.model.dto.performance.ResearcherPerformance;
import top.alvinsite.demo.model.entity.ProjectMember;
import top.alvinsite.demo.model.entity.performance.CrossingProject;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.ScoreDistributionParam;
import top.alvinsite.demo.service.ScoreDistributionService;
import top.alvinsite.demo.service.performance.CrossingProjectService;
import top.alvinsite.demo.service.rule.CrossingRuleService;
import top.alvinsite.demo.service.rule.ScoreDistributionConfigService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CrossingProjectServiceImpl implements CrossingProjectService {

    private final static String performance = "crossing";

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private ProjectMemberDao projectMemberDao;

    @Autowired
    private CrossingRuleService crossingRuleService;

    @Autowired
    private ScoreDistributionService scoreDistributionService;

    @Autowired
    private ScoreDistributionConfigService scoreDistributionConfigService;

    @Override
    public List<CrossingProject> findAll(PerformanceQuery performanceQuery){
        List<CrossingProject> list = projectDao.findCrossingProject(performanceQuery);
        list.stream().map(this::getProjectMemberNum).map(this::calcProjectPoints).collect(Collectors.toList());
        return list;
    }

    @Override
    public CrossingProject getProjectMemberNum(CrossingProject project) {
        Integer memberNum = projectMemberDao.selectCount(Wrappers.<ProjectMember>lambdaQuery().eq(ProjectMember::getProjectNum, project.getProjectNum()));
        project.setMemberNum(memberNum);
        return project;
    }


    @Override
    public CrossingProject calcProjectPoints(CrossingProject project) {
        // 读取计分规则
        float score = crossingRuleService.getScore(project);

        float proportion = scoreDistributionService.getProportion(ScoreDistributionParam.build(project, performance));

        // 返回个人得分
        project.setBudgetScore(project.getBudgetScore() * proportion);
        project.setProjectScore(project.getProjectScore() * proportion);
        project.setScore(score * proportion);
        return project;
    }

    @Override
    public void setTotalPoints(ResearcherPerformance researcherPerformance, float totalPoints) {
        researcherPerformance.setCrossingPoint(totalPoints);
    }
}
