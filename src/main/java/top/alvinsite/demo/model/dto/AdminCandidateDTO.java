package top.alvinsite.demo.model.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.entity.Department;

@Data
@Alias("AdminCandidateDTO")
public class AdminCandidateDTO {
    private String account;
    private String nickname;
    private Department department;
    private String phone;
    private Boolean join;
}
