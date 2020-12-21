package top.alvinsite.demo.model.entity.performance;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.entity.type.ProjectType;
import top.alvinsite.demo.model.enums.ProjectLevel;
import top.alvinsite.demo.model.support.ExcelColumn;

import java.util.Date;
import java.util.List;

/**
 * @author Alvin
 */
@Data
@Alias("LongitudinalProject")
@EqualsAndHashCode(callSuper = true)
public class LongitudinalProject extends BaseEntity  {

    private String projectNum;

    @ExcelColumn(value = "项目名称", col = 4)
    private String title;

    @ExcelColumn(value = "项目分类", col = 5)
    private ProjectType type;

    @ExcelColumn(value = "项目级别", col = 6)
    private ProjectLevel level;

    @ExcelColumn(value = "合同经费(万元)", col = 7)
    private float budget;

    @ExcelColumn(value = "开始时间", col = 8)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startedTime;

    @ExcelColumn(value = "结束时间", col = 9)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date finishedTime;

    @ExcelColumn(value = "组成成员", col = 10)
    private List<ManagerUserDTO> members;

    @ExcelColumn(value = "项目分", col = 11)
    private float projectScore;

    @ExcelColumn(value = "经费分", col = 12)
    private float budgetScore;
}
