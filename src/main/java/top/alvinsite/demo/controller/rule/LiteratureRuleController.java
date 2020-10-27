package top.alvinsite.demo.controller.rule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.alvinsite.demo.model.entity.rule.LiteratureRule;
import top.alvinsite.demo.model.entity.rule.LiteratureRuleFundingSource;
import top.alvinsite.demo.model.entity.rule.LiteratureRuleRevised;
import top.alvinsite.demo.model.entity.rule.LiteratureRuleTopicWithDongguan;
import top.alvinsite.demo.model.params.RuleQuery;
import top.alvinsite.demo.model.support.UserInfo;
import top.alvinsite.demo.model.vo.LiteratureRuleVO;
import top.alvinsite.demo.model.vo.RuleVO;
import top.alvinsite.demo.service.rule.LiteratureRuleService;
import xcz.annotation.PermissionClass;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static top.alvinsite.demo.utils.BeanUtils.updateProperties;

/**
 * @author Alvin
 */
@Slf4j
@RestController
@RequestMapping("performance/rule/literature")
@PermissionClass
public class LiteratureRuleController extends BaseRuleController<LiteratureRuleService, LiteratureRule> {

    public static String performance = "literature";

    public LiteratureRuleController() {
        super.setPerformance(performance);
    }

    @Override
    public RuleVO get(@RequestHeader("authorization") UserInfo userInfo, @Valid RuleQuery ruleQuery) {
        RuleVO ruleVO = super.get(userInfo, ruleQuery);

        LiteratureRuleVO literatureRuleVO = new LiteratureRuleVO();
        updateProperties(ruleVO, literatureRuleVO);

        literatureRuleVO.setFundingSourceList(baseService.getFundingSourceRules(ruleQuery));
        literatureRuleVO.setTopicWithDongguanList(baseService.getTopicWithDongguanRules(ruleQuery));
        literatureRuleVO.setRevisedList(baseService.getRevisedRules(ruleQuery));

        return literatureRuleVO;
    }


    @PostMapping("funding-source/{department}/{year}")
    public void postFundingSource(@RequestHeader("authorization") UserInfo userInfo, @Valid RuleQuery ruleQuery, @RequestBody @Valid List<LiteratureRuleFundingSource> rules) {
        // 如果用户不是系统管理员，则限定只能保存自己管理机构的数据
        addManagerLimit(userInfo, ruleQuery);

        rules.stream().map((rule -> {
            rule.setDepartment(ruleQuery.getDepartment());
            rule.setYear(ruleQuery.getYear());
            return rule;
        })).collect(Collectors.toList());

        baseService.saveFundingSourceRules(rules);
    }

    @PostMapping("topic-with-dongguan/{department}/{year}")
    public void postTopicWithDongguan(@RequestHeader("authorization") UserInfo userInfo, @Valid RuleQuery ruleQuery, @RequestBody @Valid List<LiteratureRuleTopicWithDongguan> rules) {
        // 如果用户不是系统管理员，则限定只能保存自己管理机构的数据
        addManagerLimit(userInfo, ruleQuery);

        rules.stream().map((rule -> {
            rule.setDepartment(ruleQuery.getDepartment());
            rule.setYear(ruleQuery.getYear());
            return rule;
        })).collect(Collectors.toList());

        baseService.saveTopicWithDongguanRules(rules);
    }


    @PostMapping("revised/{department}/{year}")
    public void postRevised(@RequestHeader("authorization") UserInfo userInfo, @Valid RuleQuery ruleQuery, @RequestBody @Valid List<LiteratureRuleRevised> rules) {
        // 如果用户不是系统管理员，则限定只能保存自己管理机构的数据
        addManagerLimit(userInfo, ruleQuery);

        rules.stream().map((rule -> {
            rule.setDepartment(ruleQuery.getDepartment());
            rule.setYear(ruleQuery.getYear());
            return rule;
        })).collect(Collectors.toList());

        baseService.saveRevisedRules(rules);
    }
}
