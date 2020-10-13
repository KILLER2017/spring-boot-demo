package top.alvinsite.demo.controller;

import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.support.UserInfo;

public class BaseController {
    protected void addManagerLimit(UserInfo userInfo, PerformanceQuery performanceQuery) {
        // 如果用户不是系统管理员，则限定只能查询自己管理机构的数据
        if (userInfo.getUserGroup() != "admin" && userInfo.getManageUnitId() != null) {
            performanceQuery.setDepartmentId(userInfo.getManageUnitId());
        }
    }
}
