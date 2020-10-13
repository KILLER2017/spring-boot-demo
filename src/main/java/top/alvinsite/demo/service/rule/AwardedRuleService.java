package top.alvinsite.demo.service.rule;

import top.alvinsite.demo.model.dto.rule.AwardedRuleDTO;
import top.alvinsite.demo.model.params.RuleQuery;

import java.util.List;

public interface AwardedRuleService {
    public List<AwardedRuleDTO> list(RuleQuery ruleQuery);
    public void save(List<AwardedRuleDTO> awardedRuleDTOS);
}
