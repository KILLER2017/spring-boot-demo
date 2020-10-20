package top.alvinsite.demo.controller.rule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.alvinsite.demo.model.dto.rule.PatentRuleDTO;
import top.alvinsite.demo.model.entity.rule.PatentRule;
import top.alvinsite.demo.model.params.RuleQuery;
import top.alvinsite.demo.model.support.UserInfo;
import top.alvinsite.demo.service.rule.PatentRuleService;
import xcz.annotation.PermissionClass;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("performance/rule/patent")
@PermissionClass
public class PatentRuleController extends BaseRuleController<PatentRuleService, PatentRule>{

    public static String performance = "patent";

    public PatentRuleController() {
        super.setPerformance(performance);
    }
}
