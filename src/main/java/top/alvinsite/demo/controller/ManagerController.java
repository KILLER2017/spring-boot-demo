package top.alvinsite.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import top.alvinsite.demo.dao.TeacherDao;
import top.alvinsite.demo.dao.auth.ManagerDao;
import top.alvinsite.demo.model.dto.ManagerUserCandidateDTO;
import top.alvinsite.demo.model.dto.auth.ManagerDTO;
import top.alvinsite.demo.model.entity.auth.ManagerUser;
import top.alvinsite.demo.model.param.ManagerUserQuery;
import top.alvinsite.demo.model.param.Page;
import top.alvinsite.demo.model.param.PerformanceQuery;
import top.alvinsite.framework.springsecurity.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("auth/permission/manager")
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
    public void saveManager(@AuthenticationPrincipal User user, @RequestParam Integer id, @RequestBody List<ManagerUser> managerUsers) {
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
        operatorParam.setAccount(user.getUsername());
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
