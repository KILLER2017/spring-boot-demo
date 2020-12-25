package top.alvinsite.demo.service.salary;

import top.alvinsite.demo.model.entity.salary.PerformanceWage;
import top.alvinsite.demo.model.params.PerformanceQuery;

/**
 * @author Alvin
 */
public interface PerformanceWageService extends SalaryService<PerformanceWage> {

    /**
     * 获取指定一个用户的业绩绩效工资
     * @param query 查询参数
     * @return 业绩绩效工资
     */
    Double getUserPerformanceWage(PerformanceQuery query);
}
