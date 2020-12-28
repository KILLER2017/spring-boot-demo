package top.alvinsite.demo.controller.rule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.service.rule.*;
import top.alvinsite.framework.springsecurity.entity.User;

import java.util.Arrays;
import java.util.List;

/**
 * @author Alvin
 */
@Slf4j
@RestController
@RequestMapping("performance/rule/helper")
public class RuleHelperController {
    private final static String SUPER_USER_GROUP = "admin";

    private final LongitudinalRuleService longitudinalRuleService;
    private final CrossingRuleService crossingRuleService;
    private final PaperRuleService paperRuleService;
    private final LiteratureRuleService literatureRuleService;
    private final PatentRuleService patentRuleService;
    private final CopyrightRuleService copyrightRuleService;
    private final AwardedRuleService awardedRuleService;
    private final ScoreDistributionConfigService scoreDistributionConfigService;

    public RuleHelperController(LongitudinalRuleService longitudinalRuleService, CrossingRuleService crossingRuleService, PaperRuleService paperRuleService, LiteratureRuleService literatureRuleService, PatentRuleService patentRuleService, CopyrightRuleService copyrightRuleService, AwardedRuleService awardedRuleService, ScoreDistributionConfigService scoreDistributionConfigService) {
        this.longitudinalRuleService = longitudinalRuleService;
        this.crossingRuleService = crossingRuleService;
        this.paperRuleService = paperRuleService;
        this.literatureRuleService = literatureRuleService;
        this.patentRuleService = patentRuleService;
        this.copyrightRuleService = copyrightRuleService;
        this.awardedRuleService = awardedRuleService;
        this.scoreDistributionConfigService = scoreDistributionConfigService;
    }

    @GetMapping("copy-rule")
    @Transactional(rollbackFor = Exception.class)
    public void copyPerformanceRule(String sourceDepartment, int sourceYear, String targetDepartment, int targetYear) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!SUPER_USER_GROUP.equals(user.getUserGroup())) {
            List<String> list = Arrays.asList(user.getManageUnits());
            if (!list.containsAll(Arrays.asList(sourceDepartment, targetDepartment))) {
                throw new IllegalArgumentException("只能操作您管理部门的规则");
            }
        }

        longitudinalRuleService.copyRule(sourceDepartment, sourceYear, targetDepartment, targetYear);
        crossingRuleService.copyRule(sourceDepartment, sourceYear, targetDepartment, targetYear);
        paperRuleService.copyRule(sourceDepartment, sourceYear, targetDepartment, targetYear);
        literatureRuleService.copyRule(sourceDepartment, sourceYear, targetDepartment, targetYear);
        patentRuleService.copyRule(sourceDepartment, sourceYear, targetDepartment, targetYear);
        copyrightRuleService.copyRule(sourceDepartment, sourceYear, targetDepartment, targetYear);
        awardedRuleService.copyRule(sourceDepartment, sourceYear, targetDepartment, targetYear);
        scoreDistributionConfigService.copyConfig(sourceDepartment, sourceYear, targetDepartment, targetYear);
    }
}
