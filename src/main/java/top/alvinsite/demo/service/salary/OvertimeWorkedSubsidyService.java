package top.alvinsite.demo.service.salary;

import com.baomidou.mybatisplus.extension.service.IService;
import top.alvinsite.demo.model.entity.salary.OvertimeWorkedSubsidy;
import top.alvinsite.demo.model.params.PerformanceQuery;

import java.util.List;

/**
 * @author Alvin
 */
public interface OvertimeWorkedSubsidyService extends IService<OvertimeWorkedSubsidy> {

    /**
     *
     * @param query
     * @return
     */
    List<OvertimeWorkedSubsidy> findAll(PerformanceQuery query);
}
