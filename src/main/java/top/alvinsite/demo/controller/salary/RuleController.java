package top.alvinsite.demo.controller.salary;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.alvinsite.demo.model.entity.salary.Rule;
import top.alvinsite.demo.model.params.SalaryRuleQuery;
import top.alvinsite.demo.service.salary.RuleService;

import javax.validation.Valid;
import java.util.List;


/**
 * @author Alvin<543046534@qq.com>
 */
@Slf4j
@RestController
@RequestMapping("salary/rule")
@Validated
public class RuleController {
    @Autowired
    private RuleService ruleService;

    /**
     * 列表数据接口
     * @param salaryRuleQuery 查询过滤参数
     * @return 列表数据
     */
    @GetMapping("{department}/{year}")
    public List<Rule> list(@Valid SalaryRuleQuery salaryRuleQuery) {
        return ruleService.list(salaryRuleQuery);
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("{department}/{year}")
    public void save(@Valid SalaryRuleQuery salaryRuleQuery, @RequestBody @Valid List<Rule> rules) {

        Assert.notEmpty(rules, "请添加规则后再进行保存");

        rules.forEach(rule -> {rule.setDepartment(salaryRuleQuery.getDepartment());});

        ruleService.remove(Wrappers.<Rule>lambdaQuery()
                .eq(Rule::getDepartment, salaryRuleQuery.getDepartment())
                .eq(Rule::getYear, salaryRuleQuery.getYear())
        );

        ruleService.saveBatch(rules);
    }

    @GetMapping("delete")
    public void delete(String[] id) {
        ruleService.removeById(id);
    }
}
