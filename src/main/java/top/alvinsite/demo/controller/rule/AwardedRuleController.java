package top.alvinsite.demo.controller.rule;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import top.alvinsite.demo.model.dto.rule.AwardedRuleDTO;
import top.alvinsite.demo.model.entity.rule.AwardedRule;
import top.alvinsite.demo.model.entity.rule.ScoreDistributionConfig;
import top.alvinsite.demo.model.params.RuleQuery;
import top.alvinsite.demo.model.support.UserInfo;
import top.alvinsite.demo.model.vo.RuleVO;
import top.alvinsite.demo.service.rule.AwardedRuleService;
import xcz.annotation.PermissionClass;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static top.alvinsite.demo.utils.BeanUtils.updateProperties;


@Slf4j
@RestController
@RequestMapping("performance/rule/awarded")
@PermissionClass
@Api(value = "科研获奖规则 接口集合")
public class AwardedRuleController extends BaseRuleController<AwardedRuleService, AwardedRule> {

    public static String performance = "awarded";

    public AwardedRuleController() {
        super.setPerformance(performance);
    }

    @ApiOperation(value = "获取科研获奖绩效规则", notes = "")
    @Override
    public RuleVO get(@RequestHeader("authorization") UserInfo userInfo,
                      @ApiParam(value = "desc of param" , required=true ) @Valid RuleQuery ruleQuery) {
        return super.get(userInfo, ruleQuery);
    }

    @Override
    public void post(@RequestHeader("authorization") UserInfo userInfo, @Valid RuleQuery ruleQuery, boolean useScoreDistribution, @Valid List<AwardedRule> rules) {
        super.post(userInfo, ruleQuery, useScoreDistribution, rules);
    }
}
