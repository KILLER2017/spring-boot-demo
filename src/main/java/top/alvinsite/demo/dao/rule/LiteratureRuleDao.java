package top.alvinsite.demo.dao.rule;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.rule.LiteratureRuleDTO;
import top.alvinsite.demo.model.entity.rule.LiteratureRule;
import top.alvinsite.demo.model.params.RuleQuery;

import java.util.List;

/**
 * @author Alvin
 */
@Repository
public interface LiteratureRuleDao extends BaseMapper<LiteratureRule> {
    List<LiteratureRuleDTO> findAll(RuleQuery ruleQuery);
    void saveBatch(List<LiteratureRuleDTO> literatureRuleDTOS);
    void delete(RuleQuery ruleQuery);
}
