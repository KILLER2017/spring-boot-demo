package top.alvinsite.demo.dao.rule;

import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.rule.PaperRuleDTO;
import top.alvinsite.demo.model.entity.rule.PaperRule;
import top.alvinsite.demo.model.params.RuleQuery;

import java.util.List;

@Repository
public interface PaperRuleDao {
    List<PaperRuleDTO> findAll(RuleQuery ruleQuery);
    void saveBatch(List<PaperRule> paperRules);
    void delete(RuleQuery ruleQuery);
}
