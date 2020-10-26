package top.alvinsite.demo.controller.performance;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import top.alvinsite.demo.model.entity.performance.Awarded;
import top.alvinsite.demo.model.params.Page;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.support.UserInfo;
import top.alvinsite.demo.service.performance.BasePerformanceService;

import java.util.List;

public class BaseController<M extends BasePerformanceService, T> {

    protected String controllerName = "default";

    @Autowired
    protected M baseService;

    protected void addManagerLimit(UserInfo userInfo, PerformanceQuery performanceQuery) {
        // 如果用户不是系统管理员，则限定只能查询自己管理机构的数据
        if (userInfo.getUserGroup() != "admin" && userInfo.getManageUnits() != null) {
            performanceQuery.setDepartmentScope(userInfo.getManageUnits());
        }
    }

    @GetMapping
    public PageInfo<T> get(UserInfo userInfo, Page page, PerformanceQuery performanceQuery) throws Exception {
        // 如果用户不是系统管理员，则限定只能查询自己管理机构的数据
        addManagerLimit(userInfo, performanceQuery);

        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<T> list = baseService.findAll(performanceQuery);
        return new PageInfo<>(list);
    }

    @PostMapping
    public String test() {
        return controllerName;
    }
}
