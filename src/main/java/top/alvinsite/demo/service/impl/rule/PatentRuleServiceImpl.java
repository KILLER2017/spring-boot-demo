package top.alvinsite.demo.service.impl.rule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.rule.PatentRuleDao;
import top.alvinsite.demo.model.dto.rule.LiteratureRuleDTO;
import top.alvinsite.demo.model.dto.rule.PatentRuleDTO;
import top.alvinsite.demo.model.params.RuleQuery;
import top.alvinsite.demo.service.rule.PatentRuleService;

import java.util.List;

import static top.alvinsite.demo.utils.BeanUtils.updateProperties;

@Service
public class PatentRuleServiceImpl implements PatentRuleService {
    @Autowired
    private PatentRuleDao patentRuleDao;
    @Override
    public List<PatentRuleDTO> list(RuleQuery ruleQuery) {
        return patentRuleDao.findAll(ruleQuery);
    }

    @Override
    public void save(List<PatentRuleDTO> patentRuleDTOS) {
        // 删除旧的规则
        if (patentRuleDTOS != null && !patentRuleDTOS.isEmpty()) {
            PatentRuleDTO firstRule =  patentRuleDTOS.get(0);

            RuleQuery ruleQuery = new RuleQuery();
            updateProperties(firstRule, ruleQuery);
            patentRuleDao.delete(ruleQuery);
        }

        // 保存新的规则
        patentRuleDao.saveBatch(patentRuleDTOS);
    }
}
