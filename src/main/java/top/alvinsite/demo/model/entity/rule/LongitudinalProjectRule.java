package top.alvinsite.demo.model.entity.rule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.enums.ProjectLevel;

import javax.validation.constraints.NotNull;

/**
 * @author Alvin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Alias("LongitudinalProjectRule")
public class LongitudinalProjectRule extends BaseRuleEntity  {

    @NotNull(message = "项目分类不能为空")
    private String type;

    @NotNull(message = "项目级别不能为空")
    private ProjectLevel level;

    @NotNull(message = "项目分不能为空")
    private Float projectScore;

    @NotNull(message = "经费分因子不能为空")
    private Float budgetScoreFactor;
}
