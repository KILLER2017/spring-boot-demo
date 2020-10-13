package top.alvinsite.demo.controller.performance;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.controller.BaseController;
import top.alvinsite.demo.dao.performance.ProjectDao;
import top.alvinsite.demo.model.dto.performance.LongitudinalProjectDTO;
import top.alvinsite.demo.model.entity.performance.LongitudinalProject;
import top.alvinsite.demo.model.params.Page;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.support.UserInfo;
import top.alvinsite.demo.service.performance.LongitudinalProjectService;
import xcz.annotation.PermissionClass;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("performance/longitudinal-project")
@PermissionClass
public class LongitudinalProjectController extends BaseController {
    /**
     * 项目
     */
    @Autowired
    private LongitudinalProjectService longitudinalProjectService;

    @GetMapping
    public PageInfo<LongitudinalProject> findLongitudinalProject(@RequestHeader("authorization") UserInfo userInfo,
                                                                    Page page, PerformanceQuery performanceQuery) throws Exception {
        addManagerLimit(userInfo, performanceQuery);

        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<LongitudinalProject> list = longitudinalProjectService.findAll(performanceQuery);
        return new PageInfo<>(list);
    }
}
