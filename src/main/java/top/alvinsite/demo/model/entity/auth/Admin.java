package top.alvinsite.demo.model.entity.auth;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@Alias("Admin")
public class Admin {
    private Integer id;
    private String account;
    private String createBy;
    private String updateBy;
    private Date createTime;
    private Date updateTime;
}
