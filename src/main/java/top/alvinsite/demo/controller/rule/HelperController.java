package top.alvinsite.demo.controller.rule;

import org.springframework.beans.factory.annotation.Autowired;
import top.alvinsite.demo.dao.type.DepartmentDao;

/**
 * @author Alvin<543046534@qq.com>
 */
public class HelperController {
    @Autowired
    private DepartmentDao departmentDao;

    /**
     * 将一个部门的规则复制给其它部门
     * @param year
     * @param departmentId
     */
    public void copyRule(int year, String departmentId) {
        // 获取规则

        // departmentDao.findAll().stream().forEach();
    }
}
