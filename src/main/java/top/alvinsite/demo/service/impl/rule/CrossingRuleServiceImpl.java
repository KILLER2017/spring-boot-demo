package top.alvinsite.demo.service.impl.rule;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.rule.CrossingRuleDao;
import top.alvinsite.demo.model.dto.rule.CrossingRuleDTO;
import top.alvinsite.demo.model.entity.performance.CrossingProject;
import top.alvinsite.demo.model.entity.rule.CrossingProjectRule;
import top.alvinsite.demo.model.params.RuleQuery;
import top.alvinsite.demo.service.rule.CrossingRuleService;

import java.util.List;

@Slf4j
@Service
public class CrossingRuleServiceImpl extends ServiceImpl<CrossingRuleDao, CrossingProjectRule> implements CrossingRuleService{
    @Autowired
    private CrossingRuleDao crossingRuleDao;

    @Override
    public List<CrossingRuleDTO> findAll(RuleQuery ruleQuery) {
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
    public float getScore(CrossingProject crossingProject) {
        CrossingProjectRule rule = findRule(crossingProject);

        // 计算项目总分
        float budgetScore = 0f;
        float projectScore = 0f;

        if (rule != null) {
            if (rule.getMin() <= crossingProject.getBudget() && crossingProject.getBudget() < rule.getMax()) {
                budgetScore = crossingProject.getBudget() * rule.getBudgetScoreFactor();
                projectScore = rule.getProjectScore();
            } else if (rule.getThreshold() <= crossingProject.getBudget()) {
                budgetScore = crossingProject.getBudget() * rule.getThresholdBudgetScoreFactor();
                projectScore = rule.getProjectScore() / rule.getThresholdProjectScorePer() * rule.getThresholdProjectScoreFactor();
            }
        }
        crossingProject.setBudgetScore(budgetScore);
        crossingProject.setProjectScore(projectScore);
        crossingProject.setScore(budgetScore + projectScore);
        return budgetScore + projectScore;
    }
}
