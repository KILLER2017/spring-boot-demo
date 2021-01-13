package top.alvinsite.demo.service.salary;

import top.alvinsite.demo.model.entity.salary.OvertimeWorkedSubsidy;
import top.alvinsite.demo.model.param.PerformanceQuery;

/**
 * @author Alvin
 */
public interface OvertimeWorkedSubsidyService extends SalaryService<OvertimeWorkedSubsidy> {

    /**
     * 获取指定一个用户的超课时津贴
     * @param query 查询参数
     * @return 超课时津贴
     */
    Double getUserOvertimeWorkedSubsidy(PerformanceQuery query);

}
