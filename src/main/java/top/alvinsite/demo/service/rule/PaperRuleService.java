package top.alvinsite.demo.service.rule;

import top.alvinsite.demo.model.dto.rule.PaperRuleDTO;
import top.alvinsite.demo.model.entity.rule.PaperRule;
import top.alvinsite.demo.model.params.RuleQuery;

import java.util.List;

public interface PaperRuleService {
    public List<PaperRuleDTO> list(RuleQuery ruleQuery);
    public void save(List<PaperRule> paperRules);
}
