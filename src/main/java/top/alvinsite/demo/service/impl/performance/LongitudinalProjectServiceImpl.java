package top.alvinsite.demo.service.impl.performance;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.ProjectMemberDao;
import top.alvinsite.demo.dao.performance.ProjectDao;
import top.alvinsite.demo.dao.rule.ScoreDistributionDao;
import top.alvinsite.demo.model.entity.ProjectMember;
import top.alvinsite.demo.model.entity.performance.LongitudinalProject;
import top.alvinsite.demo.model.entity.rule.LongitudinalProjectRule;
import top.alvinsite.demo.model.entity.rule.ScoreDistribution;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.ScoreDistributionParam;
import top.alvinsite.demo.service.ScoreDistributionService;
import top.alvinsite.demo.service.performance.LongitudinalProjectService;
import top.alvinsite.demo.service.rule.LongitudinalRuleService;
import top.alvinsite.demo.service.rule.ScoreDistributionConfigService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LongitudinalProjectServiceImpl implements LongitudinalProjectService {
    private final static String performance = "longitudinal";

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private ProjectMemberDao projectMemberDao;
    @Autowired
    private LongitudinalRuleService longitudinalRuleService;

    @Autowired
    private ScoreDistributionService scoreDistributionService;

    @Autowired
    private ScoreDistributionConfigService scoreDistributionConfigService;





    @Override
    public List<LongitudinalProject> findAll(PerformanceQuery performanceQuery) throws Exception {
        List<LongitudinalProject> list = projectDao.findLongitudinalProject(performanceQuery);
        list.stream().map(this::getProjectMemberNum).map(this::calcTotalPoints).collect(Collectors.toList());
        return list;
    }

    @Override
    public LongitudinalProject getProjectMemberNum(LongitudinalProject project) {
        Integer memberNum = projectMemberDao.selectCount(Wrappers.<ProjectMember>lambdaQuery().eq(ProjectMember::getProjectNum, project.getProjectNum()));
        project.setMemberNum(memberNum);
        return project;
    }

    @Override
    public LongitudinalProject calcTotalPoints(LongitudinalProject project) {
        // 读取计分规则
        LongitudinalProjectRule rule = longitudinalRuleService.findOneByLongitudinalProject(project);

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
        ScoreDistributionParam param = new ScoreDistributionParam(
                project.getDepartment(),
                this.performance,
                project.getApprovalProjectYear(),
                project.getMemberNum(), project.
                getSignedOrder());

        float proportion = scoreDistributionService.getProportion(param);
        budgetScore *= proportion;
        projectScore *= proportion;

        // 返回个人得分
        project.setBudgetScore(budgetScore);
        project.setProjectScore(projectScore);
        project.setScore(budgetScore + projectScore);
        return project;
    }
}
