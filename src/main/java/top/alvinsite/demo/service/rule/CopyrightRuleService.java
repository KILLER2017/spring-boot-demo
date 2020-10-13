package top.alvinsite.demo.service.rule;

import top.alvinsite.demo.model.dto.rule.CopyrightRuleDTO;
import top.alvinsite.demo.model.params.RuleQuery;

import java.util.List;

public interface CopyrightRuleService {
    public List<CopyrightRuleDTO> list(RuleQuery ruleQuery);
    public void save(List<CopyrightRuleDTO> copyrightRuleDTOS);
}
