package top.alvinsite.demo.model.entity.performance;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.support.ExcelColumn;

@Data
@Alias("LongitudinalProject")
public class LongitudinalProject {

    private String id;

    @ExcelColumn(value = "姓名", col = 1)
    private String nickname;

    private String departmentId;

    @ExcelColumn(value = "所属单位", col = 2)
    private String department;

    private String projectNum;

    @ExcelColumn(value = "项目名称", col = 3)
    private String title;

    private String typeId;

    @ExcelColumn(value = "项目分类", col = 4)
    private String type;

    private Integer levelId;

    @ExcelColumn(value = "项目级别", col = 5)
    private String level;

    @ExcelColumn(value = "合同经费(万元)", col = 6)
    private float budget;


    private Integer ApprovalProjectYear;

    private Integer memberNum;

    private Integer SignedOrder;

    // 项目分
    private float projectScore;
    // 经费分
    private float budgetScore;

    @ExcelColumn(value = "绩效分数", col = 7)
    private float score;
}
