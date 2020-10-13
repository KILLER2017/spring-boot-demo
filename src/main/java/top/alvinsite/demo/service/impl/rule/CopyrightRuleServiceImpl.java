package top.alvinsite.demo.service.impl.rule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.rule.CopyrightRuleDao;
import top.alvinsite.demo.model.dto.rule.CopyrightRuleDTO;
import top.alvinsite.demo.model.entity.rule.PaperRule;
import top.alvinsite.demo.model.params.RuleQuery;
import top.alvinsite.demo.service.rule.CopyrightRuleService;

import java.util.List;

import static top.alvinsite.demo.utils.BeanUtils.updateProperties;

@Service
public class CopyrightRuleServiceImpl implements CopyrightRuleService {
    @Autowired
    private CopyrightRuleDao copyrightRuleDao;

    @Override
    public List<CopyrightRuleDTO> list(RuleQuery ruleQuery) {
        return copyrightRuleDao.findAll(ruleQuery);
    }

    @Override
    public void save(List<CopyrightRuleDTO> copyrightRuleDTOS) {
        // 删除旧的规则
        if (copyrightRuleDTOS != null && !copyrightRuleDTOS.isEmpty()) {
            CopyrightRuleDTO firstRule =  copyrightRuleDTOS.get(0);

            RuleQuery ruleQuery = new RuleQuery();
            updateProperties(firstRule, ruleQuery);
            copyrightRuleDao.delete(ruleQuery);
        }

        // 保存新的规则
        copyrightRuleDao.saveBatch(copyrightRuleDTOS);
    }
}
