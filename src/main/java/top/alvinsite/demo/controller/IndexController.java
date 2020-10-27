package top.alvinsite.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.support.UserInfo;
import top.alvinsite.demo.service.performance.*;
import top.alvinsite.framework.spring.AsyncTaskManager;
import xcz.annotation.PermissionClass;

/**
 * @author Alvin
 */
@Slf4j
@RestController
@RequestMapping("performance/export")
@PermissionClass
public class IndexController {

    private final static String SUPER_USER_GROUP = "admin";

    @Value("${app.domain}")
    private String domain;

    @Autowired
    private LongitudinalProjectService longitudinalProjectService;

    @Autowired
    private CrossingProjectService crossingProjectService;

    @Autowired
    private PaperService paperService;

    @Autowired
    private LiteratureService literatureService;

    @Autowired
    private PatentService patentService;

    @Autowired
    private CopyrightService copyrightService;

    @Autowired
    private AwardedService awardedService;

    @Autowired
    private SummaryService summaryService;

    /**
     * 注入异步任务管理器
     */
    @Autowired
    AsyncTaskManager asyncTaskManager;

    protected void addManagerLimit(UserInfo userInfo, PerformanceQuery performanceQuery) {
        // 如果用户不是系统管理员，则限定只能查询自己管理机构的数据
        if (!SUPER_USER_GROUP.equals(userInfo.getUserGroup()) && userInfo.getManageUnitId() != null) {
            performanceQuery.setDepartmentId(userInfo.getManageUnitId());
        }
    }
}
