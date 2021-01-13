package top.alvinsite.demo.dao.rule;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.entity.rule.LiteratureRule;
import top.alvinsite.demo.model.param.RuleQuery;

import java.util.List;

/**
 * @author Alvin
 */
@Repository
public interface LiteratureRuleDao extends BaseMapper<LiteratureRule> {
    List<LiteratureRule> findAll(RuleQuery ruleQuery);
}
