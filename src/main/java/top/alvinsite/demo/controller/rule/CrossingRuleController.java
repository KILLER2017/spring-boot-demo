package top.alvinsite.demo.controller.rule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.model.entity.rule.CrossingProjectRule;
import top.alvinsite.demo.service.rule.CrossingRuleService;

/**
 * @author Alvin<543046534@qq.com>
 */
@Slf4j
@RestController
@RequestMapping("performance/rule/crossing-project")
public class CrossingRuleController extends AbstractRuleController<CrossingRuleService, CrossingProjectRule> {

    public static String performance = "crossing";

    public CrossingRuleController() {
        super.setPerformance(performance);
    }
}
