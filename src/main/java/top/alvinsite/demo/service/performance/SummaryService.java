package top.alvinsite.demo.service.performance;

import com.baomidou.mybatisplus.extension.service.IService;
import top.alvinsite.demo.model.dto.performance.ResearcherPerformance;
import top.alvinsite.demo.model.entity.performance.Awarded;
import top.alvinsite.demo.model.params.PerformanceQuery;

import java.util.List;

public interface SummaryService extends IService<ResearcherPerformance>, BasePerformanceService {
    List<ResearcherPerformance> findAll(PerformanceQuery performanceQuery);
}
