package top.alvinsite.demo.model.dto.performance;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.entity.type.ProjectType;
import top.alvinsite.demo.model.support.Excel;

@Data
@Alias("LongitudinalProjectDTO")
public class LongitudinalProjectDTO {

    private String id;

    @Excel(name = "姓名", col = 1)
    private String nickname;

    @Excel(name = "所属单位", col = 2)
    private String department;

    @Excel(name = "项目名称", col = 3)
    private String title;

    @Excel(name = "项目分类", col = 4)
    private ProjectType type;

    @Excel(name = "项目级别", col = 5)
    private String level;

    @Excel(name = "合同经费(万元)", col = 6)
    private float budget;

    // 项目分
    private float projectScore;
    // 经费分
    private float budgetScore;

    @Excel(name = "绩效分数", col = 7)
    private float score;
}
