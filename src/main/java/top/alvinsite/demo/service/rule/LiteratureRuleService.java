package top.alvinsite.demo.service.rule;

import top.alvinsite.demo.model.entity.performance.Literature;
import top.alvinsite.demo.model.entity.rule.LiteratureRule;
import top.alvinsite.demo.model.entity.rule.LiteratureRuleFundingSource;
import top.alvinsite.demo.model.entity.rule.LiteratureRuleRevised;
import top.alvinsite.demo.model.entity.rule.LiteratureRuleTopicWithDongguan;
import top.alvinsite.demo.model.param.RuleQuery;

import java.util.List;

/**
 * 著作成果绩效规则服务接口
 * @author Alvin
 */
public interface LiteratureRuleService extends IRuleService<Literature, LiteratureRule> {


    List<LiteratureRuleFundingSource> getFundingSourceRules(RuleQuery ruleQuery);
    List<LiteratureRuleTopicWithDongguan> getTopicWithDongguanRules(RuleQuery ruleQuery);
    List<LiteratureRuleRevised> getRevisedRules(RuleQuery ruleQuery);

    void saveFundingSourceRules(List<LiteratureRuleFundingSource> rules);
    void saveTopicWithDongguanRules(List<LiteratureRuleTopicWithDongguan> rules);
    void saveRevisedRules(List<LiteratureRuleRevised> rules);
}
