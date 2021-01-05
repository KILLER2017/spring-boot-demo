package top.alvinsite.demo.controller.salary;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.alvinsite.demo.model.entity.salary.IncentiveWage;
import top.alvinsite.demo.model.entity.salary.IncentiveWageStandard;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.salary.IncentiveWageUpdateParam;
import top.alvinsite.demo.model.validation.ValidationGroup2;
import top.alvinsite.demo.service.salary.IncentiveWageService;
import top.alvinsite.demo.service.salary.IncentiveWageStandardService;

import javax.validation.Valid;

/**
 * 超课时津贴控制器
 * @author Alvin
 */
@RestController
@RequestMapping("salary/incentive-wage")
public class IncentiveWageController extends BaseSalaryController<IncentiveWageService, IncentiveWage, IncentiveWageUpdateParam> {

    @Autowired
    private IncentiveWageStandardService standardService;

    @Override
    protected Class<IncentiveWage> getEntityClass() {
        return IncentiveWage.class;
    }

    @Override
    protected Class<IncentiveWageUpdateParam> getParamClass() {
        return IncentiveWageUpdateParam.class;
    }

    @Override
    protected String getOutputExcelName() {
        return "激励绩效工资.xlsx";
    }

    @Override
    protected String getExcelTemplateName() {
        return "激励绩效工资导出模板.xlsx";
    }

    @Override
    protected IncentiveWage handle(PerformanceQuery query, IncentiveWage entity) {
        entity.setYear(query.getYear());
        // 查询是否已存在记录
        IncentiveWage oldRecord = baseService.getOne(Wrappers.<IncentiveWage>lambdaQuery()
                        .eq(IncentiveWage::getYear, query.getYear())
                        .eq(IncentiveWage::getAccount, entity.getAccount())
                , false);
        if (oldRecord != null) {
            entity.setId(oldRecord.getId());
        }
        return entity;
    }

    @GetMapping("standard")
    public IncentiveWageStandard getIncentiveWageStandard(@Validated(ValidationGroup2.class) PerformanceQuery query) {
        return standardService.getOne(Wrappers.<IncentiveWageStandard>lambdaQuery()
                .eq(IncentiveWageStandard::getYear, query.getYear())
                .eq(IncentiveWageStandard::getDepartment, query.getDepartmentId()),
                false);
    }

    @PostMapping("standard")
    public void setIncentiveWageStandard(@Valid @RequestBody IncentiveWageStandard incentiveWageStandard) {
        standardService.saveOrUpdate(incentiveWageStandard, Wrappers.<IncentiveWageStandard>lambdaUpdate()
                .eq(IncentiveWageStandard::getYear, incentiveWageStandard.getYear())
                .eq(IncentiveWageStandard::getDepartment, incentiveWageStandard.getDepartment())
        );
    }
}
