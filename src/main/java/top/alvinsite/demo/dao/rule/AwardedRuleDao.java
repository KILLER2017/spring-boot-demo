package top.alvinsite.demo.dao.rule;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.rule.AwardedRuleDTO;
import top.alvinsite.demo.model.entity.rule.AwardedRule;
import top.alvinsite.demo.model.params.RuleQuery;

import java.util.List;

@Repository
public interface AwardedRuleDao extends BaseMapper<AwardedRule> {
    public List<AwardedRule> findAll(RuleQuery ruleQuery);
    public void saveBatch(List<AwardedRule> awardedRuleDTOS);
    public void delete(RuleQuery ruleQuery);
}
