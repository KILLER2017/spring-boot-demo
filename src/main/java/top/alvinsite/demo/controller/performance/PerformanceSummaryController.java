package top.alvinsite.demo.controller.performance;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.model.dto.performance.ResearcherPerformance;
import top.alvinsite.demo.service.performance.SummaryService;

/**
 * @author Alvin
 */
@Slf4j
@RestController
@RequestMapping("performance/summaries")
public class PerformanceSummaryController extends AbstractPerformanceController<SummaryService, ResearcherPerformance> {
}
