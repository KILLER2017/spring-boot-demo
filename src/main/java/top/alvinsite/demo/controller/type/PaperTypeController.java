package top.alvinsite.demo.controller.type;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xcz.annotation.ParameterClass;
import xcz.annotation.PermissionClass;

@Slf4j
@RestController
@RequestMapping("auth/type/paper-type")
@PermissionClass
@ParameterClass
public class PaperTypeController {
}
