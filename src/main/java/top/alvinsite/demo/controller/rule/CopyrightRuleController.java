package top.alvinsite.demo.controller.rule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.alvinsite.demo.model.dto.rule.CopyrightRuleDTO;
import top.alvinsite.demo.model.params.RuleQuery;
import top.alvinsite.demo.model.support.UserInfo;
import top.alvinsite.demo.service.rule.CopyrightRuleService;
import xcz.annotation.PermissionClass;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("performance/rule/copyright")
@PermissionClass
public class CopyrightRuleController {
    @Autowired
    private CopyrightRuleService copyrightRuleService;

    @GetMapping
    public List<CopyrightRuleDTO> list(@RequestHeader("authorization") UserInfo userInfo, RuleQuery ruleQuery) {
        // 如果用户不是系统管理员，则限定只能查询自己管理机构的数据
        if (userInfo.getUserGroup() != "admin" && userInfo.getManageUnitId() != null) {
            ruleQuery.setDepartment(userInfo.getManageUnitId());
        }
        return copyrightRuleService.list(ruleQuery);
    }

    @PostMapping
    public void save(@RequestHeader("authorization") UserInfo userInfo, @RequestBody List<CopyrightRuleDTO> copyrightRuleDTOS) {
        // 如果用户不是系统管理员，则限定只能保存自己管理机构的数据
        if (userInfo.getUserGroup() != "admin" && userInfo.getManageUnitId() != null) {
            copyrightRuleDTOS.stream().map((copyrightRuleDTO -> {
                copyrightRuleDTO.setDepartment(userInfo.getManageUnitId());
                return copyrightRuleDTO;
            })).collect(Collectors.toList());
        }
        copyrightRuleService.save(copyrightRuleDTOS);
    }
}
