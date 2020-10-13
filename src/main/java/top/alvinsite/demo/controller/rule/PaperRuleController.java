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
public class PaperRuleController {

    @Autowired
    private PaperRuleService paperRuleService;

    @GetMapping
    public List<PaperRuleDTO> list(@RequestHeader("authorization") UserInfo userInfo, RuleQuery ruleQuery) {
        // 如果用户不是系统管理员，则限定只能查询自己管理机构的数据
        if (userInfo.getUserGroup() != "admin" && userInfo.getManageUnitId() != null) {
            ruleQuery.setDepartment(userInfo.getManageUnitId());
        }
        return paperRuleService.list(ruleQuery);
    }

    @PostMapping
    public void save(@RequestHeader("authorization") UserInfo userInfo, @RequestBody List<PaperRule> paperRules) {
        // 如果用户不是系统管理员，则限定只能保存自己管理机构的数据
        if (userInfo.getUserGroup() != "admin" && userInfo.getManageUnitId() != null) {
            paperRules.stream().map((paperRule -> {
                paperRule.setDepartment(userInfo.getManageUnitId());
                return paperRule;
            })).collect(Collectors.toList());
        }
        paperRuleService.save(paperRules);
    }
}
