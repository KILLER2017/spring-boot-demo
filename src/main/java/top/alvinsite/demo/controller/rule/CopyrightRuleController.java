package top.alvinsite.demo.controller.rule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.model.entity.rule.CopyrightRule;
import top.alvinsite.demo.service.rule.CopyrightRuleService;
import xcz.annotation.PermissionClass;

/**
 * @author Alvin
 */
@Slf4j
@RestController
@RequestMapping("performance/rule/copyright")
@PermissionClass
public class CopyrightRuleController extends BaseRuleController<CopyrightRuleService, CopyrightRule> {

    public static String performance = "copyright";

    public CopyrightRuleController() {
        super.setPerformance(performance);
    }
}
