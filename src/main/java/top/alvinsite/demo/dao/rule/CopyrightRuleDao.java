package top.alvinsite.demo.dao.rule;

import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.rule.CopyrightRuleDTO;
import top.alvinsite.demo.model.params.RuleQuery;

import java.util.List;

@Repository
public interface CopyrightRuleDao {
    public List<CopyrightRuleDTO> findAll(RuleQuery ruleQuery);
    public void saveBatch(List<CopyrightRuleDTO> copyrightRuleDTOS);
    public void delete(RuleQuery ruleQuery);
}
