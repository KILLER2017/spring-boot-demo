package top.alvinsite.demo.service.rule;

import com.baomidou.mybatisplus.extension.service.IService;
import top.alvinsite.demo.model.dto.rule.AwardedRuleDTO;
import top.alvinsite.demo.model.entity.performance.Awarded;
import top.alvinsite.demo.model.entity.rule.AwardedRule;
import top.alvinsite.demo.model.params.RuleQuery;

import java.util.List;

public interface AwardedRuleService extends IRuleService<AwardedRule> {
    List<AwardedRule> list(RuleQuery ruleQuery);
    AwardedRule findRule(Awarded awarded);
    float getScore(Awarded awarded);
}
