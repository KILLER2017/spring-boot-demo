package top.alvinsite.demo.service.rule;

import top.alvinsite.demo.model.dto.rule.LongitudinalRuleDTO;
import top.alvinsite.demo.model.entity.performance.LongitudinalProject;
import top.alvinsite.demo.model.entity.rule.LongitudinalProjectRule;
import top.alvinsite.demo.model.params.RuleQuery;

import java.util.List;

public interface LongitudinalRuleService extends IRuleService<LongitudinalProject, LongitudinalProjectRule> {
    List<LongitudinalRuleDTO> findAll(RuleQuery ruleQuery);
    LongitudinalProjectRule findRule(LongitudinalProject longitudinalProject);
    float getScore(LongitudinalProject longitudinalProject);
}
