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
public class LongitudinalRuleController {
    @Autowired
    private LongitudinalRuleService longitudinalRuleService;

    @GetMapping
    public List<LongitudinalRuleDTO> list(@Valid @RequestHeader("authorization") UserInfo userInfo, @Valid RuleQuery ruleQuery) {
        // 如果用户不是系统管理员，则限定只能查询自己管理机构的数据
        if (userInfo.getUserGroup() != "admin" && userInfo.getManageUnitId() != null) {
            ruleQuery.setDepartment(userInfo.getManageUnitId());
        }
        return longitudinalRuleService.list(ruleQuery);
    }


    @PostMapping
    public void save(@RequestHeader("authorization") UserInfo userInfo, @RequestBody List<LongitudinalProjectRule> longitudinalProjectRules) {
        // 如果用户不是系统管理员，则限定只能保存自己管理机构的数据
        if (userInfo.getUserGroup() != "admin" && userInfo.getManageUnitId() != null) {
            longitudinalProjectRules.stream().map((longitudinalRule -> {
                longitudinalRule.setDepartment(userInfo.getManageUnitId());
                return longitudinalRule;
            })).collect(Collectors.toList());
        }
        longitudinalRuleService.save(longitudinalProjectRules);
    }
}
