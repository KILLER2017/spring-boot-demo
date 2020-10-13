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
import top.alvinsite.demo.model.dto.performance.CrossingProjectDTO;
import top.alvinsite.demo.model.entity.performance.CrossingProject;
import top.alvinsite.demo.model.params.Page;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.support.UserInfo;
import top.alvinsite.demo.service.performance.CrossingProjectService;
import xcz.annotation.PermissionClass;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("performance/crossing-project")
@PermissionClass
public class CrossingProjectController extends BaseController {
    /**
     * 项目
     */
    @Autowired
    private CrossingProjectService crossingProjectService;

    @GetMapping
    public PageInfo<CrossingProject> findCrossingProject(@RequestHeader("authorization") UserInfo userInfo, Page page, PerformanceQuery performanceQuery) throws Exception {
        // 如果用户不是系统管理员，则限定只能查询自己管理机构的数据
        addManagerLimit(userInfo, performanceQuery);

        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<CrossingProject> list = crossingProjectService.findAll(performanceQuery);
        return new PageInfo<>(list);
    }
}
