package top.alvinsite.demo.controller.performance;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.model.entity.performance.Copyright;
import top.alvinsite.demo.service.performance.CopyrightService;

/**
 * @author Alvin
 */
@Slf4j
@RestController
@RequestMapping("performance/copyright")
public class CopyrightController extends AbstractPerformanceController<CopyrightService, Copyright> {

}
