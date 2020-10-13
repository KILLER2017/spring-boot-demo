package top.alvinsite.demo.model.dto.performance;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.enums.ContractType;
import top.alvinsite.demo.model.support.ExcelColumn;

import java.util.List;

@Data
@Alias("CrossingProjectDTO")
public class CrossingProjectDTO {
    protected String id;

    @ExcelColumn(value = "姓名", col = 1)
    protected String nickname;

    @ExcelColumn(value = "所属单位", col = 2)
    protected String department;

    @ExcelColumn(value = "合同名称", col = 3)
    protected String title;

    @ExcelColumn(value = "合同类型", col = 4)
    private String contractType;

    @ExcelColumn(value = "合同编号", col = 5)
    private String contractNum;

    @ExcelColumn(value = "合同金额", col = 6)
    private float budget;

    private List<ManagerUserDTO> members;

    @ExcelColumn(value = "绩效分数", col = 7)
    protected float score;
}
