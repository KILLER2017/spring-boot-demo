package top.alvinsite.demo.service.rule;

import top.alvinsite.demo.model.entity.performance.Copyright;
import top.alvinsite.demo.model.entity.rule.CopyrightRule;
import top.alvinsite.demo.model.params.RuleQuery;

import java.util.List;

public interface CopyrightRuleService extends IRuleService<Copyright, CopyrightRule> {
    List<CopyrightRule> findAll(RuleQuery ruleQuery);
    CopyrightRule findRule(Copyright copyright);
    float getScore(Copyright copyright);
}
