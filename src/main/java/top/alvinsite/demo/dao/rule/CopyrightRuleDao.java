package top.alvinsite.demo.dao.rule;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.entity.rule.CopyrightRule;
import top.alvinsite.demo.model.param.RuleQuery;

import java.util.List;

/**
 * 著作权绩效规则DAO接口
 * @author Alvin
 */
@Repository
public interface CopyrightRuleDao extends BaseMapper<CopyrightRule> {
    /**
     * 获取著作权绩效规则列表
     * @param ruleQuery 过滤条件
     * @return 绩效规则列表
     */
    List<CopyrightRule> findAll(RuleQuery ruleQuery);
}
