package top.alvinsite.demo.controller.performance;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.model.entity.performance.Literature;
import top.alvinsite.demo.service.performance.LiteratureService;
import xcz.annotation.PermissionClass;

/**
 * @author Alvin
 */
@Slf4j
@RestController
@RequestMapping("performance/literature")
@PermissionClass
public class LiteratureController extends BaseController<LiteratureService, Literature> {

}
