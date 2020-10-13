package top.alvinsite.demo.service.rule;

import top.alvinsite.demo.model.dto.rule.LongitudinalRuleDTO;
import top.alvinsite.demo.model.entity.performance.LongitudinalProject;
import top.alvinsite.demo.model.entity.rule.LongitudinalProjectRule;
import top.alvinsite.demo.model.params.RuleQuery;

import java.util.List;

public interface LongitudinalRuleService {
    List<LongitudinalRuleDTO> list(RuleQuery ruleQuery);
    void save(List<LongitudinalProjectRule> longitudinalProjectRules);

    LongitudinalProjectRule findOneByLongitudinalProject(LongitudinalProject longitudinalProject);
}
