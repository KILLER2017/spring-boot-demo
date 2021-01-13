package top.alvinsite.demo.service.salary;

import top.alvinsite.demo.model.entity.salary.WorkloadTarget;
import top.alvinsite.demo.model.param.WorkloadTargetParam;

/**
 * @author Alvin
 */
public interface WorkloadTargetService extends SalaryService<WorkloadTarget> {

    /**
     * 获取指定一个目标工作量信息
     * @param param 查询参数
     * @return 目标工作量
     */
    WorkloadTarget getOne(WorkloadTargetParam param);
}
