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
import top.alvinsite.demo.model.entity.type.ProjectType;
import top.alvinsite.demo.model.enums.*;
import top.alvinsite.demo.model.support.UserInfo;
import top.alvinsite.demo.model.vo.EnumVO;
import top.alvinsite.demo.model.vo.DepartmentVO;
import top.alvinsite.demo.model.vo.ProjectTypeVO;

import java.util.ArrayList;
import java.util.List;

import static top.alvinsite.demo.utils.BeanUtils.transformFromInBatch;

/**
 * @author Alvin
 */
@Slf4j
@RestController
@RequestMapping("common")
public class CommonController {

    private final static String SUPER_USER_GROUP = "admin";

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private ProjectTypeDao projectTypeDao;

    @Autowired
    private PaperTypeDao paperTypeDao;


    @GetMapping("index")
    public String index() {
        return null;
    }

    @GetMapping("department")
    public List<DepartmentVO> getDepartmentList(@RequestHeader("authorization") UserInfo userInfo) {
        List<Department> departments;
        // 如果用户不是系统管理员，则限定只能查询自己管理机构的数据
        if (!SUPER_USER_GROUP.equals(userInfo.getUserGroup()) && userInfo.getManageUnitId() != null) {
            departments = departmentDao.findManageUnit(userInfo.getAccount());
        } else {
            departments =departmentDao.findAll();
        }

        return transformFromInBatch(departments, DepartmentVO.class);
    }

    @GetMapping("project-type")
    public List<ProjectTypeVO> getProjectTypeList() {
        List<ProjectType> projectTypes = projectTypeDao.findAll();
        return transformFromInBatch(projectTypes, ProjectTypeVO.class);
    }




    @GetMapping("project-level")
    public List<EnumVO> getProjectLevelList() {
        List<EnumVO> enumVOs = new ArrayList<>();
        for (ProjectLevel projectLevel : ProjectLevel.values()) {
            enumVOs.add(new EnumVO(projectLevel.ordinal(), projectLevel.getTitle()));
        }

        return enumVOs;
    }


    @GetMapping("book-type")
    public List<EnumVO> getBookTypeList() {

        List<EnumVO> enumVOS = new ArrayList<>();
        for (BookType item : BookType.values()) {
            enumVOS.add(new EnumVO(item.ordinal(), item.getName()));
        }

        return enumVOS;
    }

    @GetMapping("paper-type")
    public List<PaperTypeDTO> getPaperTypeList() {
        return paperTypeDao.findAll();
    }

    @GetMapping("patent-type")
    public List<EnumVO> getPatentTypeList() {
        List<EnumVO> enumVOS = new ArrayList<>();
            for (PatentType item : PatentType.values()) {
            enumVOS.add(new EnumVO(item.ordinal(), item.getName()));
        }

        return enumVOS;
    }

    @GetMapping("patent-scope")
    public List<EnumVO> getPatentScopeList() {
        List<EnumVO> enumVOS = new ArrayList<>();
        for (PatentScope item : PatentScope.values()) {
            enumVOS.add(new EnumVO(item.ordinal(), item.getName()));
        }

        return enumVOS;
    }

    @GetMapping("honor-level")
    public List<EnumVO> getHonorLevelList() {
        List<EnumVO> enumVOS = new ArrayList<>();
        for (HonorLevel item : HonorLevel.values()) {
            enumVOS.add(new EnumVO(item.ordinal(), item.getName()));
        }

        return enumVOS;
    }

    @GetMapping("honor-grade")
    public List<EnumVO> getHonorGradeList() {
        List<EnumVO> enumVOS = new ArrayList<>();
        for (HonorGrade item : HonorGrade.values()) {
            enumVOS.add(new EnumVO(item.ordinal(), item.getName()));
        }

        return enumVOS;
    }


    /**
     * 出版社级别
     */
    @GetMapping("publisher-level")
    public List<EnumVO> getPublisherLevelList() {
        List<EnumVO> enumVOS = new ArrayList<>();
        for (PublisherLevel item : PublisherLevel.values()) {
            enumVOS.add(new EnumVO(item.ordinal(), item.getTitle()));
        }

        return enumVOS;
    }

    /**
     * 著作权类型
     * @return 著作权类型列表
     */
    @GetMapping("copyright-type")
    public List<EnumVO> getCopyrightTypeList() {
        List<EnumVO> enumVOS = new ArrayList<>();
        for (CopyrightType item : CopyrightType.values()) {
            enumVOS.add(new EnumVO(item.ordinal(), item.getTitle()));
        }

        return enumVOS;
    }

    /**
     * 资助来源
     * @return 资助来源类型列表
     */
    @GetMapping("subsidize-from")
    public List<EnumVO> getSubsidizeFromList() {
        List<EnumVO> enumVOS = new ArrayList<>();
        for (SubsidizeFrom item : SubsidizeFrom.values()) {
            enumVOS.add(new EnumVO(item.ordinal(), item.getTitle()));

        }

        return enumVOS;
    }

    private List<EnumVO> get(BasicEnum t){
        List<EnumVO> enumVOS = new ArrayList<>();
        for (BasicEnum item : t.getAll()){
            enumVOS.add(new EnumVO(item.ordinal(), item.getTitle()));
        }
        return enumVOS;
    }
}
