package top.alvinsite.framework.springsecurity.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@TableName(value = "auth_group")
public class Group {
    @TableId(type = IdType.AUTO)
    private Long id;

    @NotBlank
    private String name;
}
