package top.alvinsite.demo.model.support;

import lombok.Data;
import top.alvinsite.demo.model.entity.Department;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class UserInfo implements Serializable {
    @NotBlank
    private String nickname;
    @NotBlank
    private String account;
    private Department department;
    @NotBlank
    private String userGroup;
    private String manageUnitId;
    private String[] manageUnits;
    private String openId;
    private String wxOpenId;
}
