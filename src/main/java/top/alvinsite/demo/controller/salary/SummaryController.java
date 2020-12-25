package top.alvinsite.demo.controller.salary;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import top.alvinsite.demo.model.entity.salary.PerformanceWage;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.service.salary.PerformanceWageService;
import top.alvinsite.utils.ExcelUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Administrator
 */
@Slf4j

public class SummaryController extends AbstractSalaryController<PerformanceWageService, PerformanceWage, PerformanceWage> {

    @Override
    protected Class<PerformanceWage> getEntityClass() {
        return PerformanceWage.class;
    }

    @Override
    protected Class<PerformanceWage> getParamClass() {
        return PerformanceWage.class;
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

    /**
     * 导出
     * @param query 查询过滤参数
     * @param response 请求响应
     */
    @Override
    @GetMapping("exportExcel")
    public void exportExcel(PerformanceQuery query, HttpServletResponse response) {
        List<PerformanceWage> list = baseService.findAll(query);
        ExcelUtils.writeExcel(response, list, PerformanceWage.class);
    }

}
