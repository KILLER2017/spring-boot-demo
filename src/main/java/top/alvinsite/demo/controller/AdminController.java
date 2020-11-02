package top.alvinsite.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import top.alvinsite.demo.dao.TeacherDao;
import top.alvinsite.demo.dao.auth.AdminDao;
import top.alvinsite.demo.model.dto.AdminCandidateDTO;
import top.alvinsite.demo.model.dto.auth.AdminDTO;
import top.alvinsite.demo.model.entity.auth.Admin;
import top.alvinsite.demo.model.params.Page;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.framework.springsecurity.entity.User;


import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alvin
 */
@Slf4j
@RestController
@RequestMapping("auth/permission/admin")
public class AdminController {
    @Autowired
    private AdminDao adminDao;

    @Autowired
    private TeacherDao teacherDao;

    @GetMapping
    public PageInfo<AdminDTO> getAdminList(Page page, PerformanceQuery performanceQuery) throws Exception {
        throw new Exception("邮件测试");
        // PageHelper.startPage(page.getPageNum(), page.getPageSize());
        // return new PageInfo<>(adminDao.findAll(performanceQuery));
    }

    @PostMapping
    public void saveAdmin(@AuthenticationPrincipal User user, @RequestBody List<Admin> admins) {
        admins.stream().map(admin -> {
            admin.setCreateBy(user.getUsername());
            admin.setUpdateBy(user.getUsername());
            return admin;
        }).collect(Collectors.toList());

        adminDao.saveBatch(admins);
    }

    @RequestMapping(value = {"", "delete"})
    public void deleteAdmin(Integer id) {
        adminDao.delete(id);
    }

    @GetMapping("candidate")
    public PageInfo<AdminCandidateDTO> getCandidateList(Page page, String searchKey) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        return new PageInfo<>(teacherDao.findAdminCandidate(searchKey));
    }
}
