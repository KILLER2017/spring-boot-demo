package top.alvinsite.demo.service.salary;

import top.alvinsite.demo.model.entity.salary.JobSubsidy;
import top.alvinsite.demo.model.params.PerformanceQuery;

/**
 * @author Alvin
 */
public interface JobSubsidyService extends SalaryService<JobSubsidy> {

    /**
     * 获取用户岗位津贴
     * @param query 查询参数
     * @return 用户岗位津贴
     */
    Double getUserJobSubsidy(PerformanceQuery query);

}
