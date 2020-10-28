package top.alvinsite.demo.model.vo;

import lombok.Data;
import top.alvinsite.demo.model.entity.Department;

import javax.validation.constraints.NotBlank;

import static top.alvinsite.demo.utils.BeanUtils.transformFrom;

/**
 * @author Administrator
 */
@Data
public class UserInfoVO {
    @NotBlank
    private String nickname;

    @NotBlank
    private String account;

    private DepartmentVO department;
    @NotBlank
    private String userGroup;

    private String manageUnitId;

    private String[] manageUnits;

    private String openId;

    private String wxOpenId;

    public void setDepartment(Department department) {
        this.department = transformFrom(department, DepartmentVO.class);
    }
}
