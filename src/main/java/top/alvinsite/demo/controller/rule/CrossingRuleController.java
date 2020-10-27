package top.alvinsite.demo.controller.rule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.model.entity.rule.CrossingProjectRule;
import top.alvinsite.demo.service.rule.CrossingRuleService;
import xcz.annotation.PermissionClass;

/**
 * @author Alvin
 */
@Slf4j
@RestController
@RequestMapping("performance/rule/crossing-project")
@PermissionClass
public class CrossingRuleController extends BaseRuleController<CrossingRuleService, CrossingProjectRule> {

    public static String performance = "crossing";

    public CrossingRuleController() {
        super.setPerformance(performance);
    }
}
