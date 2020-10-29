package top.alvinsite.demo.controller.performance;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.model.entity.performance.CrossingProject;
import top.alvinsite.demo.service.performance.CrossingProjectService;

/**
 * @author Alvin
 */
@Slf4j
@RestController
@RequestMapping("performance/crossing-project")
public class CrossingProjectController extends BaseController<CrossingProjectService, CrossingProject> {

}
