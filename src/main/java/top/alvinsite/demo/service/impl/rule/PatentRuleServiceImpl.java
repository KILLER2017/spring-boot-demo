package top.alvinsite.demo.service.impl.rule;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.rule.PatentRuleDao;
import top.alvinsite.demo.model.entity.performance.Patent;
import top.alvinsite.demo.model.entity.rule.PatentRule;
import top.alvinsite.demo.model.params.RuleQuery;
import top.alvinsite.demo.service.rule.PatentRuleService;

import java.util.List;

@Slf4j
@Service
public class PatentRuleServiceImpl extends ServiceImpl<PatentRuleDao, PatentRule> implements PatentRuleService {
    @Autowired
    private PatentRuleDao patentRuleDao;
    @Override
    public List<PatentRule> findAll(RuleQuery ruleQuery) {
        return patentRuleDao.findAll(ruleQuery);
    }

    @Override
    public PatentRule findRule(Patent patent) {
        PatentRule rule = patentRuleDao.selectOne(Wrappers.<PatentRule>lambdaQuery()
                .eq(PatentRule::getYear, patent.getApprovalProjectYear())
                .eq(PatentRule::getDepartment, patent.getDepartment().getId())
                .eq(PatentRule::getType, patent.getType())
                .eq(PatentRule::getScope, patent.getScope())
        );

        if (null == rule) {
            log.info("找不到对应的绩效规则， {}", patent);
        }

        return rule;
    }

    @Override
    public float getScore(Patent patent) {
        // 读取计分规则
        PatentRule rule = findRule(patent);
        return rule == null ? 0 : rule.getScore();
    }
}
