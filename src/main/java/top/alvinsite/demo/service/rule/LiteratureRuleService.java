package top.alvinsite.demo.service.rule;

import top.alvinsite.demo.model.dto.rule.LiteratureRuleDTO;
import top.alvinsite.demo.model.params.RuleQuery;

import java.util.List;

public interface LiteratureRuleService {
    public List<LiteratureRuleDTO> list(RuleQuery ruleQuery);
    public void save(List<LiteratureRuleDTO> literatureRuleDTOS);
}
