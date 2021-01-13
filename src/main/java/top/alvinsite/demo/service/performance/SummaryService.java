package top.alvinsite.demo.service.performance;

import com.baomidou.mybatisplus.extension.service.IService;
import top.alvinsite.demo.model.dto.performance.ResearcherPerformance;
import top.alvinsite.demo.model.param.PerformanceQuery;

import java.util.List;

/**
 * @author Alvin
 */
public interface SummaryService extends IService<ResearcherPerformance>, BasePerformanceService {
    @Override
    List<ResearcherPerformance> findAll(PerformanceQuery performanceQuery);
}
