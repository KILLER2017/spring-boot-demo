package top.alvinsite.demo.controller.performance;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.model.entity.performance.LongitudinalProject;
import top.alvinsite.demo.service.performance.LongitudinalProjectService;

/**
 * @author Alvin
 */
@Slf4j
@RestController
@RequestMapping("performance/longitudinal-project")
public class LongitudinalProjectController extends AbstractPerformanceController<LongitudinalProjectService, LongitudinalProject> {

}
