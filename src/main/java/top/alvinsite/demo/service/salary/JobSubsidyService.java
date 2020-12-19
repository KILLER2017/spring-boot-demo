package top.alvinsite.demo.service.salary;

import com.baomidou.mybatisplus.extension.service.IService;
import top.alvinsite.demo.model.entity.salary.JobSubsidy;
import top.alvinsite.demo.model.params.PerformanceQuery;

import java.util.List;

/**
 * @author Alvin
 */
public interface JobSubsidyService extends IService<JobSubsidy> {

    List<JobSubsidy> findAll(PerformanceQuery query);
}
