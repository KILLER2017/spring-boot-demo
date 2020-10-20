package top.alvinsite.demo.controller.performance;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.model.entity.performance.Awarded;
import top.alvinsite.demo.service.performance.AwardedService;
import xcz.annotation.PermissionClass;

@Slf4j
@RestController
@RequestMapping("performance/awarded")
@PermissionClass
public class AwardedController extends BaseController<AwardedService, Awarded>{

}
