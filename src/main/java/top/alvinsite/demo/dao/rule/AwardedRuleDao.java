package top.alvinsite.demo.dao.rule;

import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.rule.AwardedRuleDTO;
import top.alvinsite.demo.model.params.RuleQuery;

import java.util.List;

@Repository
public interface AwardedRuleDao {
    public List<AwardedRuleDTO> findAll(RuleQuery ruleQuery);
    public void saveBatch(List<AwardedRuleDTO> awardedRuleDTOS);
    public void delete(RuleQuery ruleQuery);
}
