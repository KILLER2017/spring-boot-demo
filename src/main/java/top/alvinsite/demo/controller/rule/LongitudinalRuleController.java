package top.alvinsite.demo.controller.rule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.alvinsite.demo.model.dto.rule.LongitudinalRuleDTO;
import top.alvinsite.demo.model.entity.rule.LongitudinalProjectRule;
import top.alvinsite.demo.model.params.RuleQuery;
import top.alvinsite.demo.model.support.UserInfo;
import top.alvinsite.demo.service.rule.LongitudinalRuleService;
import xcz.annotation.PermissionClass;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
