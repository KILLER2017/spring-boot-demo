package top.alvinsite.demo.controller.rule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.alvinsite.demo.model.dto.rule.PatentRuleDTO;
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
public class PatentRuleController {
    @Autowired
    private PatentRuleService patentRuleService;

    @GetMapping
    public List<PatentRuleDTO> list(@RequestHeader("authorization") UserInfo userInfo, RuleQuery ruleQuery) {
        // 如果用户不是系统管理员，则限定只能查询自己管理机构的数据
        if (userInfo.getUserGroup() != "admin" && userInfo.getManageUnitId() != null) {
            ruleQuery.setDepartment(userInfo.getManageUnitId());
        }
        return patentRuleService.list(ruleQuery);
    }

    @PostMapping
    public void save(@RequestHeader("authorization") UserInfo userInfo, @RequestBody List<PatentRuleDTO> patentRuleDTOS) {
        // 如果用户不是系统管理员，则限定只能保存自己管理机构的数据
        if (userInfo.getUserGroup() != "admin" && userInfo.getManageUnitId() != null) {
            patentRuleDTOS.stream().map((patentRuleDTO -> {
                patentRuleDTO.setDepartment(userInfo.getManageUnitId());
                return patentRuleDTO;
            })).collect(Collectors.toList());
        }
        patentRuleService.save(patentRuleDTOS);
    }
}
