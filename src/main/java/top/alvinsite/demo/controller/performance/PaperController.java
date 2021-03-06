package top.alvinsite.demo.controller.performance;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.model.entity.performance.Paper;
import top.alvinsite.demo.service.performance.PaperService;

/**
 * @author Alvin
 */
@Slf4j
@RestController
@RequestMapping("performance/paper")
public class PaperController extends AbstractPerformanceController<PaperService, Paper> {

}
