package top.alvinsite.demo.dao.rule;

import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.rule.PatentRuleDTO;
import top.alvinsite.demo.model.params.RuleQuery;

import java.util.List;

@Repository
public interface PatentRuleDao {
    List<PatentRuleDTO> findAll(RuleQuery ruleQuery);
    void saveBatch(List<PatentRuleDTO> patentRuleDTOS);
    void delete(RuleQuery ruleQuery);
}
