package top.alvinsite.demo.service.salary;

import com.baomidou.mybatisplus.extension.service.IService;
import top.alvinsite.demo.model.entity.salary.SalarySummary;
import top.alvinsite.demo.model.params.PerformanceQuery;

import java.util.List;

/**
 * @author Alvin
 */
public interface SalarySummaryService extends IService<SalarySummary> {
    List<SalarySummary> findAll(PerformanceQuery query);
}
