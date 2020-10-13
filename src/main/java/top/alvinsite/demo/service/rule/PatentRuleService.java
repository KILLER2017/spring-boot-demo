package top.alvinsite.demo.service.rule;

import top.alvinsite.demo.model.dto.rule.PatentRuleDTO;
import top.alvinsite.demo.model.params.RuleQuery;

import java.util.List;

public interface PatentRuleService {
    public List<PatentRuleDTO> list(RuleQuery ruleQuery);
    public void save(List<PatentRuleDTO> patentRuleDTOS);
}
