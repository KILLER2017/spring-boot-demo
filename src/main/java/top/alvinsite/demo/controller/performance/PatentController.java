package top.alvinsite.demo.controller.performance;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.model.entity.performance.Patent;
import top.alvinsite.demo.service.performance.PatentService;

/**
 * @author Alvin
 */
@Slf4j
@RestController
@RequestMapping("performance/patent")
public class PatentController extends AbstractPerformanceController<PatentService, Patent> {

}
