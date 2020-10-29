package top.alvinsite.demo.controller.rule;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.model.entity.rule.AwardedRule;
import top.alvinsite.demo.service.rule.AwardedRuleService;


/**
 * @author Alvin
 */
@Slf4j
@RestController
@RequestMapping("performance/rule/awarded")
@Api(value = "科研获奖规则 接口集合")
public class AwardedRuleController extends BaseRuleController<AwardedRuleService, AwardedRule> {

    public static String performance = "awarded";

    public AwardedRuleController() {
        super.setPerformance(performance);
    }

}
