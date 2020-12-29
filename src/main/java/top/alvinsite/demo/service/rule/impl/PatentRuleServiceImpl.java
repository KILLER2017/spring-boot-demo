package top.alvinsite.demo.service.rule.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.rule.PatentRuleDao;
import top.alvinsite.demo.model.entity.performance.Patent;
import top.alvinsite.demo.model.entity.rule.PatentRule;
import top.alvinsite.demo.model.params.RuleQuery;
import top.alvinsite.demo.service.rule.PatentRuleService;

import java.util.List;

/**
 * @author Alvin
 */
@Slf4j
@Service
public class PatentRuleServiceImpl extends AbstractRuleService<PatentRuleDao, Patent, PatentRule> implements PatentRuleService {
    @Autowired
    private PatentRuleDao patentRuleDao;
    @Override
    public List<PatentRule> findAll(RuleQuery ruleQuery) {
        return patentRuleDao.findAll(ruleQuery);
    }

    @Override
    public PatentRule findRule(Patent project) {
        PatentRule rule = patentRuleDao.selectOne(Wrappers.<PatentRule>lambdaQuery()
                .eq(PatentRule::getYear, project.getApprovalProjectYear())
                .eq(PatentRule::getDepartment, project.getDepartment().getId())
                .eq(PatentRule::getType, project.getType())
                .eq(PatentRule::getScope, project.getScope())
        );

        if (null == rule) {
            log.info("找不到对应的绩效规则， {}", project);
        }

        return rule;
    }

    @Override
    public double getScore(Patent project) {
        // 读取计分规则
        PatentRule rule = findRule(project);
        return rule == null ? 0 : rule.getScore();
    }
}
