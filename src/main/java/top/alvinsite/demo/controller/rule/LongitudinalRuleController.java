package top.alvinsite.demo.controller.rule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.model.entity.rule.LongitudinalProjectRule;
import top.alvinsite.demo.service.rule.LongitudinalRuleService;
import xcz.annotation.PermissionClass;

/**
 * @author Alvin
 */
@Slf4j
@RestController
@RequestMapping("performance/rule/longitudinal-project")
@PermissionClass
public class LongitudinalRuleController extends BaseRuleController<LongitudinalRuleService, LongitudinalProjectRule> {

    public static String performance = "longitudinal";

    public LongitudinalRuleController() {
        super.setPerformance(performance);
    }
}
