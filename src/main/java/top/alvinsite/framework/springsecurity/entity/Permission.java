package top.alvinsite.framework.springsecurity.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@TableName(value = "auth_permission")
public class Permission {
    @TableId(type = IdType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    private Long contentTypeId;

    @NotBlank
    private String codename;
}
