package top.alvinsite.demo.controller.rule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.model.entity.rule.LiteratureRule;
import top.alvinsite.demo.model.entity.rule.LiteratureRuleFundingSource;
import top.alvinsite.demo.model.entity.rule.LiteratureRuleRevised;
import top.alvinsite.demo.model.entity.rule.LiteratureRuleTopicWithDongguan;
import top.alvinsite.demo.model.param.RuleQuery;
import top.alvinsite.demo.model.vo.LiteratureRuleVO;
import top.alvinsite.demo.model.vo.RuleVO;
import top.alvinsite.demo.service.rule.LiteratureRuleService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static top.alvinsite.util.BeanUtils.updateProperties;

/**
 * @author Alvin<543046534@qq.com>
 */
@Slf4j
@RestController
@RequestMapping("performance/rule/literature")
public class LiteratureRuleController extends AbstractRuleController<LiteratureRuleService, LiteratureRule> {

    public static String performance = "literature";

    public LiteratureRuleController() {
        super.setPerformance(performance);
    }

    @Override
    public RuleVO get(@Valid RuleQuery ruleQuery) {
        RuleVO ruleVO = super.get(ruleQuery);

        LiteratureRuleVO literatureRuleVO = new LiteratureRuleVO();
        updateProperties(ruleVO, literatureRuleVO);

        literatureRuleVO.setFundingSourceList(baseService.getFundingSourceRules(ruleQuery));
        literatureRuleVO.setTopicWithDongguanList(baseService.getTopicWithDongguanRules(ruleQuery));
        literatureRuleVO.setRevisedList(baseService.getRevisedRules(ruleQuery));

        return literatureRuleVO;
    }


    @PostMapping("funding-source/{department}/{year}")
    public void postFundingSource(@Valid RuleQuery ruleQuery, @RequestBody @Valid List<LiteratureRuleFundingSource> rules) {
        // 如果用户不是系统管理员，则限定只能保存自己管理机构的数据
        addManagerLimit(ruleQuery);

        rules.stream().map((rule -> {
            rule.setDepartment(ruleQuery.getDepartment());
            rule.setYear(ruleQuery.getYear());
            return rule;
        })).collect(Collectors.toList());

        baseService.saveFundingSourceRules(rules);
    }

    @PostMapping("topic-with-dongguan/{department}/{year}")
    public void postTopicWithDongguan(@Valid RuleQuery ruleQuery, @RequestBody @Valid List<LiteratureRuleTopicWithDongguan> rules) {
        // 如果用户不是系统管理员，则限定只能保存自己管理机构的数据
        addManagerLimit(ruleQuery);

        rules.stream().map((rule -> {
            rule.setDepartment(ruleQuery.getDepartment());
            rule.setYear(ruleQuery.getYear());
            return rule;
        })).collect(Collectors.toList());

        baseService.saveTopicWithDongguanRules(rules);
    }


    @PostMapping("revised/{department}/{year}")
    public void postRevised(@Valid RuleQuery ruleQuery, @RequestBody @Valid List<LiteratureRuleRevised> rules) {
        // 如果用户不是系统管理员，则限定只能保存自己管理机构的数据
        addManagerLimit(ruleQuery);

        rules.stream().map((rule -> {
            rule.setDepartment(ruleQuery.getDepartment());
            rule.setYear(ruleQuery.getYear());
            return rule;
        })).collect(Collectors.toList());

        baseService.saveRevisedRules(rules);
    }
}
