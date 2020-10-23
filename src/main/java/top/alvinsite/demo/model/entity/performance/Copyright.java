package top.alvinsite.demo.model.entity.performance;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.entity.Department;
import top.alvinsite.demo.model.enums.CopyrightType;
import top.alvinsite.demo.model.support.ExcelColumn;

import java.util.List;

@Data
@Alias("Copyright")
public class Copyright extends BaseEntity  {
    private String id;

    private String account;

    @ExcelColumn(value = "姓名", col = 1)
    private String nickname;

    @ExcelColumn(value = "所属单位", col = 2)
    private Department department;

    @ExcelColumn(value = "著作权名称", col = 3)
    private String title;

    @ExcelColumn(value = "著作权类型", col = 4)
    private CopyrightType type;

    @ExcelColumn(value = "所有作者", col = 5)
    private List<ManagerUserDTO> authors;

    private Integer ApprovalProjectYear;

    private Integer memberNum;

    private Integer SignedOrder;

    @ExcelColumn(value = "绩效分数", col = 6)
    private float score;
}
