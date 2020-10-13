package top.alvinsite.demo.service.rule;

import top.alvinsite.demo.model.dto.rule.CrossingRuleDTO;
import top.alvinsite.demo.model.entity.performance.CrossingProject;
import top.alvinsite.demo.model.entity.rule.CrossingProjectRule;
import top.alvinsite.demo.model.params.RuleQuery;

import java.util.List;

public interface CrossingRuleService {
    public List<CrossingRuleDTO> list(RuleQuery ruleQuery);
    public void save(List<CrossingProjectRule> crossingProjectRules);
    CrossingProjectRule findOneByCrossingProject(CrossingProject project);
}
