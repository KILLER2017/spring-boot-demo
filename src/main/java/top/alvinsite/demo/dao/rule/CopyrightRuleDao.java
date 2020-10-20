package top.alvinsite.demo.dao.rule;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.rule.CopyrightRuleDTO;
import top.alvinsite.demo.model.entity.rule.CopyrightRule;
import top.alvinsite.demo.model.params.RuleQuery;

import java.util.List;

@Repository
public interface CopyrightRuleDao extends BaseMapper<CopyrightRule> {
    public List<CopyrightRule> findAll(RuleQuery ruleQuery);
    public void saveBatch(List<CopyrightRule> copyrightRuleS);
    public void delete(RuleQuery ruleQuery);
}
