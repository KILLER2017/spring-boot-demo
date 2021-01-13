package top.alvinsite.demo.dao.rule;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.entity.rule.AwardedRule;
import top.alvinsite.demo.model.param.RuleQuery;

import java.util.List;

/**
 * 科研获奖绩效规则 DAO 接口
 * @author Alvin
 */
@Repository
public interface AwardedRuleDao extends BaseMapper<AwardedRule> {

    /**
     * 获取科研获奖绩效规则列表
     * @param ruleQuery 绩效规则查询参数
     * @return 绩效规则列表
     */
    List<AwardedRule> findAll(RuleQuery ruleQuery);

    /**
     * 批量保存科研获奖绩效规则
     * @param awardedRuleDTOS 科研获奖绩效规则列表
     */
    void saveBatch(List<AwardedRule> awardedRuleDTOS);

    /**
     * 根据条件删除绩效规则
     * @param ruleQuery 绩效规则查询参数
     */
    void delete(RuleQuery ruleQuery);
}
