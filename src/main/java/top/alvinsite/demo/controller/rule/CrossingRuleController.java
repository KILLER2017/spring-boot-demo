package top.alvinsite.demo.controller.rule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.alvinsite.demo.model.dto.rule.CrossingRuleDTO;
import top.alvinsite.demo.model.entity.rule.CrossingProjectRule;
import top.alvinsite.demo.model.params.RuleQuery;
import top.alvinsite.demo.model.support.UserInfo;
import top.alvinsite.demo.service.rule.CrossingRuleService;
import xcz.annotation.PermissionClass;

import java.util.List;
import java.util.stream.Collectors;

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
