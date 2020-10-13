package top.alvinsite.demo.model.dto.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;
import java.util.List;

@Data
@Alias("ManagerDTO")
public class ManagerDTO {
    private Integer id;
    private String department;
    private List<ManagerUserDTO> managers;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date updateTime;
    private ManagerUserDTO updateBy;
}
