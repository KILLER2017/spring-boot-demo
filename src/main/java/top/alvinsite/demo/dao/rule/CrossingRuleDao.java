package top.alvinsite.demo.dao.rule;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.rule.CrossingRuleDTO;
import top.alvinsite.demo.model.entity.rule.CrossingProjectRule;
import top.alvinsite.demo.model.params.RuleQuery;

import java.util.List;

@Repository

public interface CrossingRuleDao extends BaseMapper<CrossingProjectRule> {
    List<CrossingRuleDTO> findAll(RuleQuery ruleQuery);
    void saveBatch(List<CrossingProjectRule> crossingProjectRules);
    void delete(RuleQuery ruleQuery);
}
