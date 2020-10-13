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
public class CrossingRuleController {
    @Autowired
    private CrossingRuleService crossingRuleService;

    @GetMapping
    public List<CrossingRuleDTO> list(@RequestHeader("authorization") UserInfo userInfo, RuleQuery ruleQuery) {
        // 如果用户不是系统管理员，则限定只能查询自己管理机构的数据
        if (userInfo.getUserGroup() != "admin" && userInfo.getManageUnitId() != null) {
            ruleQuery.setDepartment(userInfo.getManageUnitId());
        }
        return crossingRuleService.list(ruleQuery);
    }

    @PostMapping
    public void save(@RequestHeader("authorization") UserInfo userInfo, @RequestBody List<CrossingProjectRule> crossingProjectRules) {
        // 如果用户不是系统管理员，则限定只能保存自己管理机构的数据
        if (userInfo.getUserGroup() != "admin" && userInfo.getManageUnitId() != null) {
            crossingProjectRules.stream().map((crossingRule -> {
                crossingRule.setDepartment(userInfo.getManageUnitId());
                return crossingRule;
            })).collect(Collectors.toList());
        }

        crossingRuleService.save(crossingProjectRules);
    }
}
