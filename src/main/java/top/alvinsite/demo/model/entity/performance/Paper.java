package top.alvinsite.demo.model.entity.performance;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.entity.Department;
import top.alvinsite.demo.model.entity.type.PaperType;
import top.alvinsite.demo.model.support.ExcelColumn;

import java.util.List;

@Data
@Alias("Paper")
@EqualsAndHashCode(callSuper = false)
public class Paper extends BaseEntity  {
    private String id;

    @ExcelColumn(value = "姓名", col = 1)
    private String nickname;

    @ExcelColumn(value = "所属单位", col = 2)
    private Department department;

    @ExcelColumn(value = "论文题目", col = 3)
    private String title;

    @ExcelColumn(value = "发表期刊", col = 4)
    private String periodical;

    @ExcelColumn(value = "刊物类型", col = 5)
    private List<PaperType> publicationType;

    @ExcelColumn(value = "所有作者", col = 6)
    private List<ManagerUserDTO> authors;

    private Integer ApprovalProjectYear;

    private Integer memberNum;

    private Integer SignedOrder;

    @ExcelColumn(value = "绩效分数", col = 7)
    private float score;
}
