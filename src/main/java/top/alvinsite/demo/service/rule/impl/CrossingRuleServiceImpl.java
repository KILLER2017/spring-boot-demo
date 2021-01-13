package top.alvinsite.demo.service.rule.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.rule.CrossingRuleDao;
import top.alvinsite.demo.model.entity.performance.CrossingProject;
import top.alvinsite.demo.model.entity.rule.CrossingProjectRule;
import top.alvinsite.demo.model.param.RuleQuery;
import top.alvinsite.demo.service.rule.CrossingRuleService;

import java.util.List;

/**
 * @author Alvin
 */
@Slf4j
@Service
public class CrossingRuleServiceImpl extends AbstractRuleService<CrossingRuleDao, CrossingProject, CrossingProjectRule> implements CrossingRuleService{
    @Autowired
    private CrossingRuleDao crossingRuleDao;

    @Override
    public List<CrossingProjectRule> findAll(RuleQuery ruleQuery) {
        return crossingRuleDao.findAll(ruleQuery);
    }

    @Override
    public CrossingProjectRule findRule(CrossingProject project) {
        CrossingProjectRule rule = crossingRuleDao.selectOne(Wrappers.<CrossingProjectRule>lambdaQuery()
                .eq(CrossingProjectRule::getYear, project.getApprovalProjectYear())
                .eq(CrossingProjectRule::getDepartment, project.getDepartment().getId())
                );

        if (null == rule) {
            log.info("找不到对应的绩效规则， {}", project);
        }

        return rule;
    }

    @Override
    public double getScore(CrossingProject project) {
        CrossingProjectRule rule = findRule(project);

        // 计算项目总分
        float budgetScore = 0f;
        float projectScore = 0f;

        if (rule != null) {
            if (rule.getMin() <= project.getBudget() && project.getBudget() < rule.getMax()) {
                budgetScore = project.getBudget() * rule.getBudgetScoreFactor();
                projectScore = rule.getProjectScore();
            } else if (rule.getThreshold() <= project.getBudget()) {
                budgetScore = project.getBudget() * rule.getThresholdBudgetScoreFactor();
                projectScore = rule.getProjectScore() / rule.getThresholdProjectScorePer() * rule.getThresholdProjectScoreFactor();
            }
        }
        project.setBudgetScore(budgetScore);
        project.setProjectScore(projectScore);
        project.setScore(budgetScore + projectScore);
        return budgetScore + projectScore;
    }
}
