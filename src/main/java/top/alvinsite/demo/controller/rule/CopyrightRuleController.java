package top.alvinsite.demo.controller.rule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import top.alvinsite.demo.model.dto.rule.CopyrightRuleDTO;
import top.alvinsite.demo.model.entity.rule.AwardedRule;
import top.alvinsite.demo.model.entity.rule.CopyrightRule;
import top.alvinsite.demo.model.entity.rule.ScoreDistributionConfig;
import top.alvinsite.demo.model.params.RuleQuery;
import top.alvinsite.demo.model.support.UserInfo;
import top.alvinsite.demo.service.rule.CopyrightRuleService;
import xcz.annotation.PermissionClass;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
