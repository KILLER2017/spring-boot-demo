package top.alvinsite.demo.dao.rule;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.rule.LiteratureRuleDTO;
import top.alvinsite.demo.model.entity.rule.LiteratureRule;
import top.alvinsite.demo.model.params.RuleQuery;

import java.util.List;
import java.util.ListIterator;

@Repository
public interface LiteratureRuleDao extends BaseMapper<LiteratureRule> {
    public List<LiteratureRuleDTO> findAll(RuleQuery ruleQuery);
    public void saveBatch(List<LiteratureRuleDTO> literatureRuleDTOS);
    public void delete(RuleQuery ruleQuery);
}
