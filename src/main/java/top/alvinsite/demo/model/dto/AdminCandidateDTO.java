package top.alvinsite.demo.model.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.vo.DepartmentVO;

@Data
@Alias("AdminCandidateDTO")
public class AdminCandidateDTO {
    private String account;
    private String nickname;
    private DepartmentVO department;
    private String phone;
    private Boolean join;
}
