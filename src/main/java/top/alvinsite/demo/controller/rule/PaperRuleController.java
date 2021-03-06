package top.alvinsite.demo.controller.rule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.model.entity.rule.PaperRule;
import top.alvinsite.demo.service.rule.PaperRuleService;

/**
 * @author Alvin<543046534@qq.com>
 */
@Slf4j
@RestController
@RequestMapping("performance/rule/paper")
public class PaperRuleController extends AbstractRuleController<PaperRuleService, PaperRule> {

    public static String performance = "paper";

    public PaperRuleController() {
        super.setPerformance(performance);
    }
}
