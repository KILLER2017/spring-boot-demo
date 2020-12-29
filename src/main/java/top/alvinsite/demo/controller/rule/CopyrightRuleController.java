package top.alvinsite.demo.controller.rule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.model.entity.rule.CopyrightRule;
import top.alvinsite.demo.service.rule.CopyrightRuleService;

/**
 * @author Alvin<543046534@qq.com>
 */
@Slf4j
@RestController
@RequestMapping("performance/rule/copyright")
public class CopyrightRuleController extends AbstractRuleController<CopyrightRuleService, CopyrightRule> {

    public static String performance = "copyright";

    public CopyrightRuleController() {
        super.setPerformance(performance);
    }
}
