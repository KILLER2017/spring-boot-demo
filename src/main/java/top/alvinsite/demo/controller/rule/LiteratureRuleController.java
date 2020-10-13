package top.alvinsite.demo.controller.rule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.alvinsite.demo.model.dto.rule.LiteratureRuleDTO;
import top.alvinsite.demo.model.entity.rule.PaperRule;
import top.alvinsite.demo.model.params.RuleQuery;
import top.alvinsite.demo.model.support.UserInfo;
import top.alvinsite.demo.service.rule.LiteratureRuleService;
import xcz.annotation.PermissionClass;

import java.util.List;
import java.util.stream.Collectors;

import static top.alvinsite.demo.utils.BeanUtils.updateProperties;

@Slf4j
@RestController
@RequestMapping("performance/rule/literature")
@PermissionClass
public class LiteratureRuleController {
    @Autowired
    private LiteratureRuleService literatureRuleService;

    @GetMapping
    public List<LiteratureRuleDTO> list(@RequestHeader("authorization") UserInfo userInfo, RuleQuery ruleQuery) {
        // 如果用户不是系统管理员，则限定只能查询自己管理机构的数据
        if (userInfo.getUserGroup() != "admin" && userInfo.getManageUnitId() != null) {
            ruleQuery.setDepartment(userInfo.getManageUnitId());
        }
        return literatureRuleService.list(ruleQuery);
    }

    @PostMapping
    public void save(@RequestHeader("authorization") UserInfo userInfo, @RequestBody List<LiteratureRuleDTO> literatureRuleDTOS) {
        // 如果用户不是系统管理员，则限定只能保存自己管理机构的数据
        if (userInfo.getUserGroup() != "admin" && userInfo.getManageUnitId() != null) {
            literatureRuleDTOS.stream().map((literatureRuleDTO -> {
                literatureRuleDTO.setDepartment(userInfo.getManageUnitId());
                return literatureRuleDTO;
            })).collect(Collectors.toList());
        }
        literatureRuleService.save(literatureRuleDTOS);
    }
}
