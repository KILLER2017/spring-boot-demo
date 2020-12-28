package top.alvinsite.demo.model.entity.rule;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.enums.SubsidizeFrom;

import javax.validation.constraints.NotNull;

/**
 * @author Alvin
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("LiteratureRuleFundingSource")
public class LiteratureRuleFundingSource extends BaseRuleEntity  {

    @NotNull(message = "资助来源类型不能为空")
    private SubsidizeFrom type;

    @NotNull(message = "资助来源绩效分数不能为空")
    private Float score;
}
