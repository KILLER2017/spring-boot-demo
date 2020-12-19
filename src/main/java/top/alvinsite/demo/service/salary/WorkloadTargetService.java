package top.alvinsite.demo.service.salary;

import com.baomidou.mybatisplus.extension.service.IService;
import top.alvinsite.demo.model.entity.salary.WorkloadTarget;
import top.alvinsite.demo.model.params.WorkloadTargetParam;

/**
 * @author Alvin
 */
public interface WorkloadTargetService extends IService<WorkloadTarget> {

    WorkloadTarget getOne(WorkloadTargetParam param);
}
