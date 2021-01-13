package top.alvinsite.demo.dao.rule;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.entity.rule.CrossingProjectRule;
import top.alvinsite.demo.model.param.RuleQuery;

import java.util.List;

/**
 * @author Alvin
 */
@Repository

public interface CrossingRuleDao extends BaseMapper<CrossingProjectRule> {

    /**
     * 获取横向项目绩效规则列表
     * @param ruleQuery 过滤条件
     * @return 绩效规则列表
     */
    List<CrossingProjectRule> findAll(RuleQuery ruleQuery);

}
