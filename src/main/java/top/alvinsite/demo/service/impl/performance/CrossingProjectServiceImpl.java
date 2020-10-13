package top.alvinsite.demo.service.impl.performance;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.ProjectMemberDao;
import top.alvinsite.demo.dao.performance.ProjectDao;
import top.alvinsite.demo.model.entity.ProjectMember;
import top.alvinsite.demo.model.entity.performance.CrossingProject;
import top.alvinsite.demo.model.entity.rule.CrossingProjectRule;
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
        list.stream().map(this::getProjectMemberNum).map(this::calcTotalPoints).collect(Collectors.toList());
        return list;
    }

    @Override
    public CrossingProject getProjectMemberNum(CrossingProject project) {
        Integer memberNum = projectMemberDao.selectCount(Wrappers.<ProjectMember>lambdaQuery().eq(ProjectMember::getProjectNum, project.getProjectNum()));
        project.setMemberNum(memberNum);
        return project;
    }


    @Override
    public CrossingProject calcTotalPoints(CrossingProject project) {
        // 读取计分规则
        CrossingProjectRule rule = crossingRuleService.findOneByCrossingProject(project);

        // 计算项目总分
        float budgetScore = 0f;
        float projectScore = 0f;

        if (rule == null) {
            return project;
        } else {
            budgetScore = project.getBudget() / rule.getBudgetScoreFactor();
            projectScore = rule.getProjectScore();
        }

        // 查询是否采用分值分配法
        boolean useScoreDistribute = scoreDistributionConfigService.useScoreDistribute(
                project.getApprovalProjectYear(),
                project.getDepartmentId(),
                this.performance);

        if (useScoreDistribute) {
            ScoreDistributionParam param = new ScoreDistributionParam(project.getApprovalProjectYear(), project.getMemberNum(), project.getSignedOrder());
            float proportion = scoreDistributionService.getProportion(param);
            budgetScore *= proportion;
            projectScore *= proportion;
        }

        // 返回个人得分
        project.setBudgetScore(budgetScore);
        project.setProjectScore(projectScore);
        project.setScore(budgetScore + projectScore);
        return project;
    }
}
