package top.alvinsite.demo.controller.salary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import top.alvinsite.demo.dao.salary.RuleDao;
import top.alvinsite.demo.model.entity.salary.Rule;
import top.alvinsite.demo.service.salary.RuleService;
import xcz.annotation.PermissionClass;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("salary/rule")
@PermissionClass
public class RuleController {
    @Autowired
    private RuleService ruleService;

    @GetMapping
    public List<Rule> list(Integer year) {
        Assert.notNull(year, "year must be not null");
        return ruleService.list(year);
    }

    @PostMapping
    public void save(String deleteIds, @RequestBody List<Rule> rules) {
        for (Rule item : rules) {
            Assert.notNull(item.getPostType(), "类型岗位不能为空");
            Assert.notNull(item.getRule(), String.format("%s的计算规则不能为空", item.getPostType()));
        }
        ruleService.saveBatch(deleteIds, rules);
    }

    @GetMapping("delete")
    public void delete(String[] id) {
        log.info(String.valueOf(id));
        ruleService.delete(id);
    }
}
