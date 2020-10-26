package top.alvinsite.demo.model.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Alvin
 */
@Data
@TableName(value = "gxky0107")
public class ProjectMember {
    private String id;

    @TableField(value = "xmbh")
    private String projectNum;

    @TableField(value = "xmzcyzgh")
    private String account;
}
