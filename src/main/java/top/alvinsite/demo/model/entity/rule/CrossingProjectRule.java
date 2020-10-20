package top.alvinsite.demo.model.entity.rule;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotNull;

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
    private Integer min;

    @NotNull(message = "合同经费缺少最小值")
    private Integer max;

    @NotNull(message = "项目分不能为空")
    private Float projectScore;

    @NotNull(message = "经费分因子不能为空")
    private Float budgetScoreFactor;
}
