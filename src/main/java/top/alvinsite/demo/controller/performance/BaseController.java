package top.alvinsite.demo.controller.performance;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import top.alvinsite.demo.model.params.Page;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.support.UserInfo;
import top.alvinsite.demo.service.performance.BasePerformanceService;

import java.util.List;

/**
 * @author Alvin
 */
public class BaseController<M extends BasePerformanceService, T> {

    protected String controllerName = "default";

    private final static String SUPER_USER_GROUP = "admin";

    @Autowired
    protected M baseService;

    protected void addManagerLimit(UserInfo userInfo, PerformanceQuery performanceQuery) {
        // 如果用户不是系统管理员，则限定只能查询自己管理机构的数据
        if (!SUPER_USER_GROUP.equals(userInfo.getUserGroup()) && userInfo.getManageUnits() != null) {
            performanceQuery.setDepartmentScope(userInfo.getManageUnits());
        }
    }

    @GetMapping
    public PageInfo get(@RequestHeader("authorization") UserInfo userInfo, Page page, PerformanceQuery performanceQuery) throws Exception {
        // 如果用户不是系统管理员，则限定只能查询自己管理机构的数据
        addManagerLimit(userInfo, performanceQuery);

        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List list = baseService.findAll(performanceQuery);
        return new PageInfo<T>(list);
    }

    @PostMapping
    public String test() {
        return controllerName;
    }
}
