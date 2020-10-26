package top.alvinsite.demo.dao.rule;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.entity.rule.PatentRule;
import top.alvinsite.demo.model.params.RuleQuery;

import java.util.List;

/**
 * @author Alvin
 */
@Repository
public interface PatentRuleDao extends BaseMapper<PatentRule> {
    List<PatentRule> findAll(RuleQuery ruleQuery);
    void saveBatch(List<PatentRule> patentRules);
    void delete(RuleQuery ruleQuery);
}
