package top.alvinsite.demo.model.dto.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@Alias("AdminDTO")
public class AdminDTO {
    private Integer id;
    private String nickname;
    private String account;
    private String phone;
    private String department;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date updateTime;
    private ManagerUserDTO updateBy;
}
