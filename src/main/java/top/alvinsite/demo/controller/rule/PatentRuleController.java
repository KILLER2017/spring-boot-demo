package top.alvinsite.demo.controller.rule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.model.entity.rule.PatentRule;
import top.alvinsite.demo.service.rule.PatentRuleService;

/**
 * @author Alvin<543046534@qq.com>
 */
@Slf4j
@RestController
@RequestMapping("performance/rule/patent")
public class PatentRuleController extends AbstractRuleController<PatentRuleService, PatentRule> {

    public static String performance = "patent";

    public PatentRuleController() {
        super.setPerformance(performance);
    }
}
