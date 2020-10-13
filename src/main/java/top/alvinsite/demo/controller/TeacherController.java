package top.alvinsite.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.dao.TeacherDao;
import top.alvinsite.demo.model.entity.Teacher;
import xcz.annotation.PermissionClass;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("auth/permission/user")
@PermissionClass
public class TeacherController {
    @Autowired
    private TeacherDao teacherDao;

    @GetMapping
    public List<Teacher> list() {
        return teacherDao.findAll();
    }
}
