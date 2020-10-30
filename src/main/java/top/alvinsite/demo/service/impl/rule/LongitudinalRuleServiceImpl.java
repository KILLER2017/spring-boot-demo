package top.alvinsite.demo.service.impl.rule;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.rule.LongitudinalRuleDao;
import top.alvinsite.demo.model.dto.rule.LongitudinalRuleDTO;
import top.alvinsite.demo.model.entity.performance.LongitudinalProject;
import top.alvinsite.demo.model.entity.rule.LongitudinalProjectRule;
import top.alvinsite.demo.model.params.RuleQuery;
import top.alvinsite.demo.service.rule.LongitudinalRuleService;

import java.util.List;

@Slf4j
@Service
public class LongitudinalRuleServiceImpl extends ServiceImpl<LongitudinalRuleDao, LongitudinalProjectRule> implements LongitudinalRuleService {
    @Autowired
    private LongitudinalRuleDao longitudinalRuleDao;

    @Override
    public List<LongitudinalProjectRule> findAll(RuleQuery ruleQuery) {
        return longitudinalRuleDao.findAll(ruleQuery);
    }

    @Override
    public LongitudinalProjectRule findRule(LongitudinalProject project) {
        LongitudinalProjectRule rule = longitudinalRuleDao.selectOne(Wrappers.<LongitudinalProjectRule>lambdaQuery()
                .eq(LongitudinalProjectRule::getYear, project.getApprovalProjectYear())
                .eq(LongitudinalProjectRule::getDepartment, project.getDepartment().getId())
                .eq(LongitudinalProjectRule::getType, project.getType().getId())
                .eq(LongitudinalProjectRule::getLevel, project.getLevel()
                ));

        if (null == rule) {
            log.info("找不到对应的绩效规则， {}", project);
        }

        return rule;
    }

    @Override
    public float getScore(LongitudinalProject project) {
        // 读取计分规则
        LongitudinalProjectRule rule = findRule(project);

        // 计算项目总分
        float budgetScore = 0f;
        float projectScore = 0f;

        if (rule != null) {
            budgetScore = project.getBudget() * rule.getBudgetScoreFactor();
            projectScore = rule.getProjectScore();
        }
        project.setBudgetScore(budgetScore);
        project.setProjectScore(projectScore);
        project.setScore(budgetScore + projectScore);
        return budgetScore + projectScore;
    }
}
