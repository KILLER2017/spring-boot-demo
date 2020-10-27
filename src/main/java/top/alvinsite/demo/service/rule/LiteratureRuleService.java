package top.alvinsite.demo.service.rule;

import top.alvinsite.demo.model.dto.rule.LiteratureRuleDTO;
import top.alvinsite.demo.model.entity.performance.Literature;
import top.alvinsite.demo.model.entity.rule.LiteratureRule;
import top.alvinsite.demo.model.entity.rule.LiteratureRuleFundingSource;
import top.alvinsite.demo.model.entity.rule.LiteratureRuleRevised;
import top.alvinsite.demo.model.entity.rule.LiteratureRuleTopicWithDongguan;
import top.alvinsite.demo.model.params.RuleQuery;

import java.util.List;

public interface LiteratureRuleService extends IRuleService<LiteratureRule> {
    List<LiteratureRuleDTO> list(RuleQuery ruleQuery);
    void save(List<LiteratureRuleDTO> literatureRuleDTOS);
    LiteratureRule findRule(Literature literature);
    float getScore(Literature literature);
    List<LiteratureRuleFundingSource> getFundingSourceRules(RuleQuery ruleQuery);
    List<LiteratureRuleTopicWithDongguan> getTopicWithDongguanRules(RuleQuery ruleQuery);
    List<LiteratureRuleRevised> getRevisedRules(RuleQuery ruleQuery);

    void saveFundingSourceRules(List<LiteratureRuleFundingSource> rules);
    void saveTopicWithDongguanRules(List<LiteratureRuleTopicWithDongguan> rules);
    void saveRevisedRules(List<LiteratureRuleRevised> rules);
}
