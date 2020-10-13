package top.alvinsite.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.alvinsite.demo.dao.TeacherDao;
import top.alvinsite.demo.dao.auth.ManagerDao;
import top.alvinsite.demo.model.dto.ManagerUserCandidateDTO;
import top.alvinsite.demo.model.dto.auth.ManagerDTO;
import top.alvinsite.demo.model.entity.auth.ManagerUser;
import top.alvinsite.demo.model.params.ManagerUserQuery;
import top.alvinsite.demo.model.params.Page;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.support.UserInfo;
import xcz.annotation.PermissionClass;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("auth/permission/manager")
@PermissionClass
public class ManagerController {

    @Autowired
    private ManagerDao managerDao;

    @Autowired
    private TeacherDao teacherDao;

    @GetMapping
    public PageInfo<ManagerDTO> getManagerList(Page page, PerformanceQuery performanceQuery) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        return new PageInfo<>(managerDao.findAll(performanceQuery));
    }

    @PostMapping
    public void saveManager(@RequestHeader("authorization") UserInfo userInfo, @RequestParam Integer id, @RequestBody List<ManagerUser> managerUsers) {
        // 删除旧的机构管理员
        managerDao.deleteManagerUser(id);
        String departmentId = managerDao.findDepartmentById(id);
        // 保存新的机构管理员
        managerUsers.stream().map(managerUser -> {
            managerUser.setManager(id);
            return managerUser;
        }).collect(Collectors.toList());
        managerDao.saveBatch(managerUsers);

        PerformanceQuery operatorParam = new PerformanceQuery();
        operatorParam.setAccount(userInfo.getAccount());
        operatorParam.setDepartmentId(departmentId);
        managerDao.updateOperator(operatorParam);
    }

    @RequestMapping(value = {"", "delete"})
    public void deleteManager(Integer id) {
        managerDao.setInactive(id);
        managerDao.deleteManagerUser(id);

    }

    @GetMapping("reset")
    public void resetManager() {
        managerDao.reset();
    }


    @GetMapping("candidate")
    public PageInfo<ManagerUserCandidateDTO> getCandidateList(Page page, String searchKey) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        return new PageInfo<>(teacherDao.findManagerUserCandidate(searchKey));
    }

    @GetMapping("department")
    public List<ManagerDTO> getManagerListByDepartment(ManagerUserQuery managerUserQuery) {
        log.info(String.valueOf(managerUserQuery));
        return managerDao.findAllByDepartment(managerUserQuery);
    }
}
