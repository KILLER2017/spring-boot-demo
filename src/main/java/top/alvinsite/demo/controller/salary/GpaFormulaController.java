package top.alvinsite.demo.controller.salary;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.alvinsite.demo.model.entity.salary.GpaFormula;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.SalaryRuleQuery;
import top.alvinsite.demo.service.salary.GpaFormulaService;

import javax.validation.Valid;
import java.util.List;


/**
 * @author Alvin<543046534@qq.com>
 */
@Slf4j
@RestController
@RequestMapping("salary/gpa-formula")
@Validated
public class GpaFormulaController {
    @Autowired
    private GpaFormulaService gpaFormulaService;

    /**
     * 列表数据接口
     * @param salaryRuleQuery 查询过滤参数
     * @return 列表数据
     */
    @GetMapping("{department}/{year}")
    public List<GpaFormula> list(@Valid SalaryRuleQuery salaryRuleQuery) {
        return gpaFormulaService.findAll(salaryRuleQuery);
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("{departmentId}/{year}")
    public void save(@Valid PerformanceQuery query, @RequestBody @Valid List<GpaFormula> gpaFormulas) {

        Assert.notEmpty(gpaFormulas, "请添加规则后再进行保存");

        gpaFormulas.forEach(rule -> rule.setDepartment(query.getDepartmentId()));

        gpaFormulaService.remove(Wrappers.<GpaFormula>lambdaQuery()
                .eq(GpaFormula::getDepartment, query.getDepartmentId())
                .eq(GpaFormula::getYear, query.getYear())
        );

        gpaFormulaService.saveBatch(gpaFormulas);
    }

}
