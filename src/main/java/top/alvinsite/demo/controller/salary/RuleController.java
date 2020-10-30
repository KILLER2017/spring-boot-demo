package top.alvinsite.demo.controller.salary;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.alvinsite.demo.model.entity.salary.Rule;
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

    @GetMapping("{year}")
    public List<Rule> list(@PathVariable Integer year) {
        Assert.notNull(year, "year must be not null");
        return ruleService.list(year);
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("{year}")
    public void save(@PathVariable Integer year, @RequestBody @Valid List<Rule> rules) {
        Assert.notEmpty(rules, "请添加规则后再进行保存");
        if (year != null) {
            ruleService.remove(Wrappers.<Rule>lambdaQuery().eq(Rule::getYear , year));
        }

        ruleService.saveBatch(rules);
    }

    @GetMapping("delete")
    public void delete(String[] id) {
        ruleService.removeById(id);
    }
}
