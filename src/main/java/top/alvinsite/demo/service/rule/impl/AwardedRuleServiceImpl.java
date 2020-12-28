package top.alvinsite.demo.service.rule.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.rule.AwardedRuleDao;
import top.alvinsite.demo.model.entity.performance.Awarded;
import top.alvinsite.demo.model.entity.rule.AwardedRule;
import top.alvinsite.demo.model.params.RuleQuery;
import top.alvinsite.demo.service.rule.AwardedRuleService;

import java.util.List;

/**
 * @author Alvin
 */
@Slf4j
@Service
public class AwardedRuleServiceImpl extends AbstractRuleService<AwardedRuleDao, Awarded, AwardedRule> implements AwardedRuleService {

    @Override
    public List<AwardedRule> findAll(RuleQuery ruleQuery) {
        return baseMapper.findAll(ruleQuery);
    }



    @Override
    public AwardedRule findRule(Awarded project) {
        AwardedRule rule = baseMapper.selectOne(Wrappers.<AwardedRule>lambdaQuery()
                .eq(AwardedRule::getYear, project.getApprovalProjectYear())
                .eq(AwardedRule::getDepartment, project.getDepartment().getId())
                .eq(AwardedRule::getLevel, project.getLevel())
                .eq(AwardedRule::getGrade, project.getGrade())
        );

        if (null == rule) {
            log.info("找不到对应的绩效规则， {}", project);
        }

        return rule;
    }

    @Override
    public float getScore(Awarded project) {
        AwardedRule rule = findRule(project);
        return rule == null ? 0 : rule.getScore();
    }
}
