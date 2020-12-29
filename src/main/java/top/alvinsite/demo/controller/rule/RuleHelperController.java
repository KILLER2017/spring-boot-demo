package top.alvinsite.demo.controller.rule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.service.rule.*;
import top.alvinsite.framework.springsecurity.entity.User;

import java.util.*;

/**
 * @author Alvin<543046534@qq.com>
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

    public void test1() {
        String sourceDepartment = "20900";
        int sourceYear = 2019;
        String[][] targets = new String[][] {
                {"20800", "2018"},
                {"20900", "2018"},
                {"21000", "2018"},
                {"21100", "2018"},
                {"21200", "2018"},
                {"21300", "2018"},
                {"21400", "2018"},
                {"21500", "2018"},
                {"21600", "2018"},
                {"21700", "2018"},
                {"21800", "2018"},

                {"20800", "2019"},
                {"20900", "2019"},
                {"21000", "2019"},
                {"21100", "2019"},
                {"21200", "2019"},
                {"21300", "2019"},
                {"21400", "2019"},
                {"21500", "2019"},
                {"21600", "2019"},
                {"21700", "2019"},
                {"21800", "2019"},

                {"20800", "2020"},
                {"20900", "2020"},
                {"21000", "2020"},
                {"21100", "2020"},
                {"21200", "2020"},
                {"21300", "2020"},
                {"21400", "2020"},
                {"21500", "2020"},
                {"21600", "2020"},
                {"21700", "2020"},
                {"21800", "2020"},
        };

        Arrays.stream(targets).forEach(item -> copyPerformanceRule(sourceDepartment, sourceYear, item[0], Integer.parseInt(item[1])));
    }

    public void test2() {
        String sourceDepartment = "20100";
        int sourceYear = 2019;
        String[][] targets = new String[][] {
                {"20100", "2018"},
                {"20200", "2018"},
                {"20300", "2018"},
                {"20400", "2018"},
                {"20500", "2018"},
                {"20600", "2018"},
                {"20700", "2018"},

                {"20100", "2019"},
                {"20200", "2019"},
                {"20300", "2019"},
                {"20400", "2019"},
                {"20500", "2019"},
                {"20600", "2019"},
                {"20700", "2019"},

                {"20100", "2020"},
                {"20200", "2020"},
                {"20300", "2020"},
                {"20400", "2020"},
                {"20500", "2020"},
                {"20600", "2020"},
                {"20700", "2020"},
        };

        Arrays.stream(targets).forEach(item -> copyPerformanceRule(sourceDepartment, sourceYear, item[0], Integer.parseInt(item[1])));
    }

    @GetMapping("test")
    @Transactional(rollbackFor = Exception.class)
    public void test() {
        test1();
        test2();
    }
}
