package top.alvinsite.demo.controller.rule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.alvinsite.demo.model.dto.rule.PaperRuleDTO;
import top.alvinsite.demo.model.entity.rule.PaperRule;
import top.alvinsite.demo.model.params.RuleQuery;
import top.alvinsite.demo.model.support.UserInfo;
import top.alvinsite.demo.service.rule.PaperRuleService;
import xcz.annotation.PermissionClass;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("performance/rule/paper")
@PermissionClass
public class PaperRuleController extends BaseRuleController<PaperRuleService, PaperRule> {

    public static String performance = "paper";

    public PaperRuleController() {
        super.setPerformance(performance);
    }
}
