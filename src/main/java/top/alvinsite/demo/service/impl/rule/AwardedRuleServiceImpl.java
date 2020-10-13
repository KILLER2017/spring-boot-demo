package top.alvinsite.demo.service.impl.rule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.rule.AwardedRuleDao;
import top.alvinsite.demo.model.dto.rule.AwardedRuleDTO;
import top.alvinsite.demo.model.params.RuleQuery;
import top.alvinsite.demo.service.rule.AwardedRuleService;

import java.util.List;

import static top.alvinsite.demo.utils.BeanUtils.updateProperties;


@Service
public class AwardedRuleServiceImpl implements AwardedRuleService {
    @Autowired
    private AwardedRuleDao awardedRuleDao;

    @Override
    public List<AwardedRuleDTO> list(RuleQuery ruleQuery) {
        return awardedRuleDao.findAll(ruleQuery);
    }

    @Override
    public void save(List<AwardedRuleDTO> awardedRuleDTOS) {
        // 删除旧的规则
        if (awardedRuleDTOS != null && !awardedRuleDTOS.isEmpty()) {
            AwardedRuleDTO firstRule =  awardedRuleDTOS.get(0);

            RuleQuery ruleQuery = new RuleQuery();
            updateProperties(firstRule, ruleQuery);
            awardedRuleDao.delete(ruleQuery);
        }

        // 保存新的规则
        awardedRuleDao.saveBatch(awardedRuleDTOS);
    }
}
