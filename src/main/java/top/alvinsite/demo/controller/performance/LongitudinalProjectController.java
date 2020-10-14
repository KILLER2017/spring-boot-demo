package top.alvinsite.demo.controller.performance;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.controller.BaseController;
import top.alvinsite.demo.model.entity.performance.LongitudinalProject;
import top.alvinsite.demo.service.performance.LongitudinalProjectService;
import xcz.annotation.PermissionClass;

@Slf4j
@RestController
@RequestMapping("performance/longitudinal-project")
@PermissionClass
public class LongitudinalProjectController extends BaseController<LongitudinalProjectService, LongitudinalProject> {

}
