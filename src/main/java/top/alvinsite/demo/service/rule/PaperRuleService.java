package top.alvinsite.demo.service.rule;

import top.alvinsite.demo.model.dto.rule.PaperRuleDTO;
import top.alvinsite.demo.model.entity.performance.Paper;
import top.alvinsite.demo.model.entity.rule.PaperRule;
import top.alvinsite.demo.model.params.RuleQuery;

import java.util.List;

public interface PaperRuleService extends IRuleService<Paper, PaperRule> {
    List<PaperRuleDTO> findAll(RuleQuery ruleQuery);
    PaperRule findRule(Paper paper);
    float getScore(Paper paper);
}
