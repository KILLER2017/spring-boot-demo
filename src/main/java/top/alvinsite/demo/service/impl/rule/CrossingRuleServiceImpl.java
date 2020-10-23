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

import static top.alvinsite.demo.utils.BeanUtils.updateProperties;

@Slf4j
@Service
public class CrossingRuleServiceImpl extends ServiceImpl<CrossingRuleDao, CrossingProjectRule> implements CrossingRuleService{
    @Autowired
    private CrossingRuleDao crossingRuleDao;

    @Override
    public List<CrossingRuleDTO> list(RuleQuery ruleQuery) {
        return crossingRuleDao.findAll(ruleQuery);
    }

    @Override
    public void save(List<CrossingProjectRule> crossingProjectRules) {
        // 删除旧的规则
        if (crossingProjectRules != null && !crossingProjectRules.isEmpty()) {
            CrossingProjectRule firstRule =  crossingProjectRules.get(0);

            RuleQuery ruleQuery = new RuleQuery();
            updateProperties(firstRule, ruleQuery);
            crossingRuleDao.delete(ruleQuery);
        }

        // 保存新的规则
        crossingRuleDao.saveBatch(crossingProjectRules);
    }

    @Override
    public CrossingProjectRule findOneByCrossingProject(CrossingProject project) {
        CrossingProjectRule rule = crossingRuleDao.selectOne(Wrappers.<CrossingProjectRule>lambdaQuery()
                .eq(CrossingProjectRule::getYear, project.getApprovalProjectYear())
                .eq(CrossingProjectRule::getDepartment, project.getDepartment().getId())
                .le(CrossingProjectRule::getMin, project.getBudget())
                .gt(CrossingProjectRule::getMax, project.getBudget())
                );

        if (null == rule) {
            log.info("找不到对应的绩效规则， {}", project);
        }

        return rule;
    }

    @Override
    public float getScore(CrossingProject crossingProject) {
        CrossingProjectRule rule = findOneByCrossingProject(crossingProject);

        // 计算项目总分
        float budgetScore = 0f;
        float projectScore = 0f;

        if (rule != null) {
            budgetScore = crossingProject.getBudget() * rule.getBudgetScoreFactor();
            projectScore = rule.getProjectScore();
        }
        crossingProject.setBudgetScore(budgetScore);
        crossingProject.setProjectScore(projectScore);
        crossingProject.setScore(budgetScore + projectScore);
        return budgetScore + projectScore;
    }
}
