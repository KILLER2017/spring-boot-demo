package top.alvinsite.demo.controller.salary;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.alvinsite.demo.model.entity.salary.ClassFeesStandard;
import top.alvinsite.demo.model.entity.salary.OvertimeWorkedSubsidy;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.salary.OvertimeWorkedSubsidyUpdateParam;
import top.alvinsite.demo.model.validation.ValidationGroup2;
import top.alvinsite.demo.service.salary.ClassFeesStandardService;
import top.alvinsite.demo.service.salary.OvertimeWorkedSubsidyService;

import javax.validation.Valid;
import java.util.List;

/**
 * 超课时津贴控制器
 * @author Alvin
 */
@RestController
@RequestMapping("salary/overtime-worked-subsidy")
public class OvertimeWorkedSubsidyController extends BaseSalaryController<OvertimeWorkedSubsidyService, OvertimeWorkedSubsidy, OvertimeWorkedSubsidyUpdateParam> {

    @Autowired
    private ClassFeesStandardService standardService;

    @Override
    protected Class<OvertimeWorkedSubsidy> getEntityClass() {
        return OvertimeWorkedSubsidy.class;
    }

    @Override
    protected Class<OvertimeWorkedSubsidyUpdateParam> getParamClass() {
        return OvertimeWorkedSubsidyUpdateParam.class;
    }

    @Override
    protected String getOutputExcelName() {
        return "超课时津贴.xlsx";
    }

    @Override
    protected String getExcelTemplateName() {
        return "超课时津贴导入模板.xlsx";
    }

    @Override
    protected OvertimeWorkedSubsidy handle(PerformanceQuery query, OvertimeWorkedSubsidy entity) {
        // 查询是否已存在记录
        OvertimeWorkedSubsidy oldRecord = baseService.getOne(Wrappers.<OvertimeWorkedSubsidy>lambdaQuery()
                        .eq(OvertimeWorkedSubsidy::getYear, entity.getYear())
                        .eq(OvertimeWorkedSubsidy::getAccount, entity.getAccount())
                , false);
        if (oldRecord != null) {
            entity.setId(oldRecord.getId());
        }
        return entity;
    }

    @GetMapping("standard")
    public List<ClassFeesStandard> getClassFeesStandard(@Validated(ValidationGroup2.class) PerformanceQuery query) {
        return standardService.list(Wrappers.<ClassFeesStandard>lambdaQuery()
                        .eq(ClassFeesStandard::getYear, query.getYear())
                        .eq(ClassFeesStandard::getDepartment, query.getDepartmentId())
                );
    }

    @PostMapping("standard")
    public void setClassFeesStandard(@Valid @RequestBody List<ClassFeesStandard> standards) {
        standards.forEach(item ->
            standardService.saveOrUpdate(item, Wrappers.<ClassFeesStandard>lambdaUpdate()
                    .eq(ClassFeesStandard::getYear, item.getYear())
                    .eq(ClassFeesStandard::getDepartment, item.getDepartment())
                    .eq(ClassFeesStandard::getTechnicalPostsLevel, item.getTechnicalPostsLevel())
            )
        );
    }
}
