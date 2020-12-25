package top.alvinsite.demo.controller.salary;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.model.entity.salary.ClassFeesStandard;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.service.salary.ClassFeesStandardService;

/**
 * @author Alvin
 */
@RestController
@RequestMapping("salary/class-fees-standard")
public class ClassFeesStandardController extends AbstractSalaryController<ClassFeesStandardService, ClassFeesStandard, ClassFeesStandard> {


    @Override
    protected Class<ClassFeesStandard> getEntityClass() {
        return ClassFeesStandard.class;
    }

    @Override
    protected Class<ClassFeesStandard> getParamClass() {
        return getEntityClass();
    }

    @Override
    protected String getOutputExcelName() {
        return "课时费标准.xlsx";
    }

    @Override
    protected String getExcelTemplateName() {
        return "课时费标准导入模板.xlsx";
    }

    @Override
    protected ClassFeesStandard handle(PerformanceQuery query, ClassFeesStandard entity) {
        return entity;
    }
}
