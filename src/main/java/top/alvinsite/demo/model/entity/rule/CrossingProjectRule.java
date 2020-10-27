package top.alvinsite.demo.model.entity.rule;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotNull;

/**
 * @author Alvin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("CrossingProjectRule")
public class CrossingProjectRule extends BaseRuleEntity  {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String department;
    private Integer year;

    @NotNull(message = "合同经费缺少最小值")
    private Float min;

    @NotNull(message = "合同经费缺少最小值")
    private Float max;

    @NotNull(message = "项目分不能为空")
    private Float projectScore;

    @NotNull(message = "经费分因子不能为空")
    private Float budgetScoreFactor;

    @NotNull(message = "合同经费规则二上限不能为空")
    private Float threshold;

    @NotNull(message = "合同经费规则二项目分因子不能为空")
    private Float thresholdProjectScoreFactor;

    private Float thresholdProjectScorePer;

    @NotNull(message = "合同经费规则二经费分因子不能为空")
    private Float thresholdBudgetScoreFactor;
}
