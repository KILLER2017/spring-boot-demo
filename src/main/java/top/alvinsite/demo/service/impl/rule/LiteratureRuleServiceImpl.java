package top.alvinsite.demo.service.impl.rule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.rule.LiteratureRuleDao;
import top.alvinsite.demo.model.dto.rule.LiteratureRuleDTO;
import top.alvinsite.demo.model.params.RuleQuery;
import top.alvinsite.demo.service.rule.LiteratureRuleService;

import java.util.List;

import static top.alvinsite.demo.utils.BeanUtils.updateProperties;

@Service
public class LiteratureRuleServiceImpl implements LiteratureRuleService {
    @Autowired
    private LiteratureRuleDao literatureRuleDao;

    @Override
    public List<LiteratureRuleDTO> list(RuleQuery ruleQuery) {
        return literatureRuleDao.findAll(ruleQuery);
    }

    @Override
    public void save(List<LiteratureRuleDTO> literatureRuleDTOS) {
        // 删除旧的规则
        if (literatureRuleDTOS != null && !literatureRuleDTOS.isEmpty()) {
            LiteratureRuleDTO firstRule =  literatureRuleDTOS.get(0);

            RuleQuery ruleQuery = new RuleQuery();
            updateProperties(firstRule, ruleQuery);
            literatureRuleDao.delete(ruleQuery);
        }

        // 保存新的规则
        literatureRuleDao.saveBatch(literatureRuleDTOS);
    }
}
