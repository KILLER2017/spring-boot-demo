package top.alvinsite.demo.service.performance;

import top.alvinsite.demo.model.dto.performance.ResearcherPerformance;
import top.alvinsite.demo.model.params.PerformanceQuery;

import java.util.List;

public interface SummaryService {
    List<ResearcherPerformance> findAll(PerformanceQuery performanceQuery);
}
