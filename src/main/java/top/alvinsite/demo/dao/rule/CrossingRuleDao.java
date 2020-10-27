package top.alvinsite.demo.dao.rule;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.rule.CrossingRuleDTO;
import top.alvinsite.demo.model.entity.rule.CrossingProjectRule;
import top.alvinsite.demo.model.params.RuleQuery;

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
    List<CrossingRuleDTO> findAll(RuleQuery ruleQuery);

    /**
     * 批量保存绩效规则
     * @param crossingProjectRules 横向项目绩效规则列表
     */
    void saveBatch(List<CrossingProjectRule> crossingProjectRules);

}
