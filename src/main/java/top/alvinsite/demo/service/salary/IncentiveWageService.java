package top.alvinsite.demo.service.salary;

import top.alvinsite.demo.model.entity.salary.IncentiveWage;
import top.alvinsite.demo.model.params.PerformanceQuery;

/**
 * @author Alvin
 */
public interface IncentiveWageService extends SalaryService<IncentiveWage> {

    /**
     * 获取用户指定年度的激励绩效工资
     * @param query 查询过滤参数
     * @return 用户激励绩效工资
     */
    Double getUserIncentiveWage(PerformanceQuery query);
}
