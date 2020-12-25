package top.alvinsite.demo.controller.salary;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.model.entity.salary.SalarySummary;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.salary.SpecialSubsidyUpdateParam;
import top.alvinsite.demo.service.salary.SalarySummaryService;

/**
 * @author Alvin
 */
@RestController
@RequestMapping("salary/summary")
public class SalaryDetailController extends AbstractSalaryController<SalarySummaryService, SalarySummary, SpecialSubsidyUpdateParam> {


    @Override
    protected Class<SalarySummary> getEntityClass() {
        return SalarySummary.class;
    }

    @Override
    protected Class<SpecialSubsidyUpdateParam> getParamClass() {
        return SpecialSubsidyUpdateParam.class;
    }

    @Override
    protected String getOutputExcelName() {
        return "工资详情.xlsx";
    }

    @Override
    protected String getExcelTemplateName() {
        return "双肩挑工资导入模板.xlsx";
    }

    @Override
    protected SalarySummary handle(PerformanceQuery query, SalarySummary entity) {
        // 查询是否已存在记录
        entity.setYear(query.getYear());
        SalarySummary oldRecord = baseService.getOne(Wrappers.<SalarySummary>lambdaQuery()
                        .eq(SalarySummary::getYear, entity.getYear())
                        .eq(SalarySummary::getAccount, entity.getAccount())
                , false);
        if (oldRecord != null) {
            entity.setId(oldRecord.getId());
        }
        return entity;
    }
}
