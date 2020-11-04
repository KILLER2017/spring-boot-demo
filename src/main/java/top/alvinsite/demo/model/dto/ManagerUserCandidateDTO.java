package top.alvinsite.demo.model.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.vo.DepartmentVO;

/**
 * @author Administrator
 */
@Data
@Alias("ManagerUserCandidateDTO")
public class ManagerUserCandidateDTO {
    private String account;
    private String nickname;
    private DepartmentVO department;
    private String phone;
    private Boolean join;
}
