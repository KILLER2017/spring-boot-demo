package top.alvinsite.demo.service.rule;

import top.alvinsite.demo.model.entity.performance.Patent;
import top.alvinsite.demo.model.entity.rule.PatentRule;
import top.alvinsite.demo.model.params.RuleQuery;

import java.util.List;

public interface PatentRuleService extends IRuleService<PatentRule> {
    List<PatentRule> list(RuleQuery ruleQuery);
    void save(List<PatentRule> patentRuleDTOS);
    PatentRule findRule(Patent patent);
    float getScore(Patent patent);
}
