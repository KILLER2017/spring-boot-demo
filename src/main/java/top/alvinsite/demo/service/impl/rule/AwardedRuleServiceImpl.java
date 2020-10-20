package top.alvinsite.demo.service.impl.rule;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.alvinsite.demo.dao.rule.AwardedRuleDao;
import top.alvinsite.demo.dao.rule.ScoreDistributionConfigDao;
import top.alvinsite.demo.model.dto.rule.AwardedRuleDTO;
import top.alvinsite.demo.model.entity.performance.Awarded;
import top.alvinsite.demo.model.entity.rule.AwardedRule;
import top.alvinsite.demo.model.entity.rule.CopyrightRule;
import top.alvinsite.demo.model.entity.rule.ScoreDistributionConfig;
import top.alvinsite.demo.model.params.RuleQuery;
import top.alvinsite.demo.service.rule.AwardedRuleService;

import java.util.List;

import static top.alvinsite.demo.utils.BeanUtils.updateProperties;

@Slf4j
@Service
public class AwardedRuleServiceImpl extends ServiceImpl<AwardedRuleDao, AwardedRule> implements AwardedRuleService {

    @Override
    public List<AwardedRule> list(RuleQuery ruleQuery) {
        return baseMapper.findAll(ruleQuery);
    }



    @Override
    public AwardedRule findRule(Awarded awarded) {
        AwardedRule rule = baseMapper.selectOne(Wrappers.<AwardedRule>lambdaQuery()
                .eq(AwardedRule::getYear, awarded.getApprovalProjectYear())
                .eq(AwardedRule::getDepartment, awarded.getDepartment().getId())
                .eq(AwardedRule::getLevel, awarded.getLevel())
                .eq(AwardedRule::getGrade, awarded.getGrade())
        );

        if (null == rule) {
            log.info("找不到对应的绩效规则， {}", awarded);
        }

        return rule;
    }

    @Override
    public float getScore(Awarded awarded) {
        AwardedRule rule = findRule(awarded);
        return rule == null ? 0 : rule.getScore();
    }
}
