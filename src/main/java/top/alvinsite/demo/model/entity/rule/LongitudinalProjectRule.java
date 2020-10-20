package top.alvinsite.demo.model.entity.rule;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.enums.ProjectLevel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("LongitudinalProjectRule")
public class LongitudinalProjectRule extends BaseRuleEntity  {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String department;
    private Integer year;

    @NotNull(message = "项目分类不能为空")
    private String type;

    @NotNull(message = "项目级别不能为空")
    private ProjectLevel level;

    @NotNull(message = "项目分不能为空")
    private Float projectScore;

    @NotNull(message = "经费分因子不能为空")
    private Float budgetScoreFactor;
}
