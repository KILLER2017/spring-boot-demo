package top.alvinsite.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.service.rule.*;

/**
 * @author Alvin
 */
@Slf4j
@RestController
@RequestMapping("test")
public class TestController {

    private LongitudinalRuleService longitudinalRuleService;
    private CrossingRuleService crossingRuleService;
    private PaperRuleService paperRuleService;
    private LiteratureRuleService literatureRuleService;
    private PatentRuleService patentRuleService;
    private CopyrightRuleService copyrightRuleService;
    private AwardedRuleService awardedRuleService;

    public TestController(LongitudinalRuleService longitudinalRuleService, CrossingRuleService crossingRuleService, PaperRuleService paperRuleService, LiteratureRuleService literatureRuleService, PatentRuleService patentRuleService, CopyrightRuleService copyrightRuleService, AwardedRuleService awardedRuleService) {
        this.longitudinalRuleService = longitudinalRuleService;
        this.crossingRuleService = crossingRuleService;
        this.paperRuleService = paperRuleService;
        this.literatureRuleService = literatureRuleService;
        this.patentRuleService = patentRuleService;
        this.copyrightRuleService = copyrightRuleService;
        this.awardedRuleService = awardedRuleService;
    }

    @GetMapping("copy-rule")
    public void copyPerformanceRule(String sourceDepartment, int sourceYear, String targetDepartment, int targetYear) {
        longitudinalRuleService.copyRule(sourceDepartment, sourceYear, targetDepartment, targetYear);
        crossingRuleService.copyRule(sourceDepartment, sourceYear, targetDepartment, targetYear);
        paperRuleService.copyRule(sourceDepartment, sourceYear, targetDepartment, targetYear);
        literatureRuleService.copyRule(sourceDepartment, sourceYear, targetDepartment, targetYear);
        patentRuleService.copyRule(sourceDepartment, sourceYear, targetDepartment, targetYear);
        copyrightRuleService.copyRule(sourceDepartment, sourceYear, targetDepartment, targetYear);
        awardedRuleService.copyRule(sourceDepartment, sourceYear, targetDepartment, targetYear);

    }



}
