package top.alvinsite.demo.controller.performance;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.entity.performance.LongitudinalProject;
import top.alvinsite.demo.model.params.Page;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.support.UserInfo;
import top.alvinsite.demo.service.performance.LongitudinalProjectService;
import xcz.annotation.PermissionClass;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alvin
 */
@Slf4j
@RestController
@RequestMapping("performance/longitudinal-project")
@PermissionClass
public class LongitudinalProjectController extends BaseController<LongitudinalProjectService, LongitudinalProject> {
    @Override
    public PageInfo<LongitudinalProject> get(@RequestHeader("authorization") UserInfo userInfo, Page page, PerformanceQuery performanceQuery) throws Exception {

        return (PageInfo<LongitudinalProject>) super.get(userInfo, page, performanceQuery);
    }
}
