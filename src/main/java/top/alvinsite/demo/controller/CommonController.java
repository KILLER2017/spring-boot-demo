package top.alvinsite.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.dao.type.*;
import top.alvinsite.demo.model.entity.Department;
import top.alvinsite.demo.model.dto.type.*;
import top.alvinsite.demo.model.support.UserInfo;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("common")
public class CommonController {
    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private ProjectTypeDao projectTypeDao;

    @Autowired
    private ProjectLevelDao projectLevelDao;

    @Autowired
    private BookTypeDao bookTypeDao;

    @Autowired
    private PaperTypeDao paperTypeDao;

    @Autowired
    private PatentTypeDao patentTypeDao;

    @Autowired
    private PatentScopeDao patentScopeDao;

    @Autowired
    private HonorLevelDao honorLevelDao;

    @Autowired
    private HonorGradeDao honorGradeDao;



    @GetMapping("index")
    public String index() {
        return null;
    }

    @GetMapping("department")
    public List<Department> getDepartmentList(@RequestHeader("authorization") UserInfo userInfo) {
        // 如果用户不是系统管理员，则限定只能查询自己管理机构的数据
        if (userInfo.getUserGroup() != "admin" && userInfo.getManageUnitId() != null) {
            return departmentDao.findManageUnit(userInfo.getAccount());
        }
        return departmentDao.findAll();
    }

    @GetMapping("project-type")
    public List<ProjectTypeDTO> getProjectTypeList() {
        return projectTypeDao.findAll();
    }

    @GetMapping("project-level")
    public List<ProjectLevelDTO> getProjectLevelList() {
        return projectLevelDao.findAll();
    }

    @GetMapping("book-type")
    public List<BookTypeDTO> getBookTypeList() {
        return bookTypeDao.findAll();
    }

    @GetMapping("paper-type")
    public List<PaperTypeDTO> getPaperTypeList() {
        return paperTypeDao.findAll();
    }

    @GetMapping("patent-type")
    public List<PatentTypeDTO> getPatentTypeList() {
        return patentTypeDao.findAll();
    }

    @GetMapping("patent-scope")
    public List<PatentScopeDTO> getPatentScopeList() {
        return patentScopeDao.findAll();
    }

    @GetMapping("honor-level")
    public List<HonorLevelDTO> getHonorLevelList() {
        return honorLevelDao.findAll();
    }

    @GetMapping("honor-grade")
    public List<HonorGradeDTO> getHonorGradeList() {
        return honorGradeDao.findAll();
    }
}
