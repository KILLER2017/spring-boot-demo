package top.alvinsite.demo.model.entity.auth;

import lombok.Data;

import java.util.Date;

/**
 * @author Alvin
 */
@Data
public class ManagerUser {
    private Integer id;
    private Integer manager;
    private String account;
    private String createBy;
    private String updateBy;
    private String remarks;
    private Date createTime;
    private Date updateTime;
}
