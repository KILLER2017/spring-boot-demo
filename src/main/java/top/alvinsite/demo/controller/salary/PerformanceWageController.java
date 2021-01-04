package top.alvinsite.demo.controller.salary;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.alvinsite.demo.model.entity.salary.PerformanceWage;
import top.alvinsite.demo.model.entity.salary.PerformanceWageFormula;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.salary.PerformanceWageUpdateParam;
import top.alvinsite.demo.service.salary.PerformanceSalaryFormulaService;
import top.alvinsite.demo.service.salary.PerformanceWageService;

import javax.validation.Valid;

import static top.alvinsite.utils.BeanUtils.transformFrom;

/**
 * @author Alvin
 */
@Slf4j
@RestController
@RequestMapping("salary/performance-wage")
public class PerformanceWageController extends AbstractSalaryController<PerformanceWageService, PerformanceWage, PerformanceWageUpdateParam> {

    @Autowired
    private PerformanceSalaryFormulaService formulaService;

    @Override
    protected Class<PerformanceWage> getEntityClass() {
        return PerformanceWage.class;
    }

    @Override
    protected Class<PerformanceWageUpdateParam> getParamClass() {
        return PerformanceWageUpdateParam.class;
    }

    @Override
    protected String getOutputExcelName() {
        return "岗位绩效考核导出数据.xlsx";
    }

    @Override
    protected String getExcelTemplateName() {
        return "岗位绩效考核导入模板.xlsx";
    }

    @Override
    protected PerformanceWage handle(PerformanceQuery query, PerformanceWage entity) {
        entity.setYear(query.getYear());
        PerformanceWage performanceWage = baseService.getOne(Wrappers.<PerformanceWage>lambdaQuery()
                        .eq(PerformanceWage::getAccount, entity.getAccount())
                        .eq(PerformanceWage::getYear, entity.getYear())
                ,false
        );

        if (performanceWage != null) {
            entity.setId(performanceWage.getId());
        }
        return entity;
    }

    @GetMapping("formula")
    public PerformanceWageFormula getFormula(PerformanceQuery query) {
        return formulaService.getOne(Wrappers.<PerformanceWageFormula>lambdaQuery()
                .eq(PerformanceWageFormula::getYear, query.getYear())
                .eq(PerformanceWageFormula::getDepartment, query.getDepartmentId())
                , false
        );
    }

    @PostMapping("formula")
    public void setFormula(@Valid @RequestBody PerformanceWageFormula formula) {
        formulaService.saveOrUpdate(formula, Wrappers.<PerformanceWageFormula>lambdaUpdate()
                .eq(PerformanceWageFormula::getYear, formula.getYear())
                .eq(PerformanceWageFormula::getDepartment, formula.getDepartment())
        );
    }

    @Override
    public void update(@RequestBody PerformanceWageUpdateParam record) {
        PerformanceWage item = transformFrom(record, getEntityClass());
        assert item != null;
        baseService.saveOrUpdate(item, Wrappers.<PerformanceWage>lambdaUpdate()
                .eq(PerformanceWage::getAccount, item.getAccount())
                .eq(PerformanceWage::getYear, item.getYear())
        );
    }

    @Override
    public void updateByPost(@RequestBody PerformanceWageUpdateParam record) {
        update(record);
    }
}
