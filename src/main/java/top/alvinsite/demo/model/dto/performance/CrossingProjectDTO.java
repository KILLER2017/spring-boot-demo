package top.alvinsite.demo.model.dto.performance;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.support.Excel;

import java.util.List;

@Data
@Alias("CrossingProjectDTO")
public class CrossingProjectDTO {
    private String id;

    @Excel(name = "姓名", col = 1)
    private String nickname;

    @Excel(name = "所属单位", col = 2)
    private String department;

    @Excel(name = "合同名称", col = 3)
    private String title;

    @Excel(name = "合同类型", col = 4)
    private String contractType;

    @Excel(name = "合同编号", col = 5)
    private String contractNum;

    @Excel(name = "合同金额", col = 6)
    private float budget;

    private List<ManagerUserDTO> members;

    @Excel(name = "绩效分数", col = 7)
    private float score;
}
