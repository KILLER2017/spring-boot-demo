package top.alvinsite.demo.model.entity.performance;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.entity.Department;
import top.alvinsite.demo.model.entity.type.ProjectType;
import top.alvinsite.demo.model.enums.ProjectLevel;
import top.alvinsite.demo.model.support.ExcelColumn;

import java.util.List;

@Data
@Alias("LongitudinalProject")
@EqualsAndHashCode(callSuper = false)
public class LongitudinalProject extends BaseEntity  {

    private String id;

    private String account;

    @ExcelColumn(value = "姓名", col = 1)
    private String nickname;

    @ExcelColumn(value = "所属单位", col = 2)
    private Department department;

    private String projectNum;

    @ExcelColumn(value = "项目名称", col = 3)
    private String title;

    @ExcelColumn(value = "项目分类", col = 4)
    private ProjectType type;

    @ExcelColumn(value = "项目级别", col = 5)
    private ProjectLevel level;

    @ExcelColumn(value = "合同经费(万元)", col = 6)
    private float budget;


    private Integer ApprovalProjectYear;

    private Integer memberNum;

    private Integer SignedOrder;

    // 项目分
    private float projectScore;
    // 经费分
    private float budgetScore;

    private List<ManagerUserDTO> members;

    @ExcelColumn(value = "绩效分数", col = 7)
    private float score;
}
