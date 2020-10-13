package top.alvinsite.demo.dao.rule;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.rule.LongitudinalRuleDTO;
import top.alvinsite.demo.model.entity.rule.LongitudinalProjectRule;
import top.alvinsite.demo.model.params.RuleQuery;

import java.util.List;

@Repository
public interface LongitudinalRuleDao extends BaseMapper<LongitudinalProjectRule> {
    List<LongitudinalRuleDTO> findAll(RuleQuery ruleQuery);
    void saveBatch(List<LongitudinalProjectRule> longitudinalProjectRules);
    void delete(RuleQuery ruleQuery);
}
