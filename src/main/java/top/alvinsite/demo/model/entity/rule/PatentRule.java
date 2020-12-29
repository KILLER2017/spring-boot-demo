package top.alvinsite.demo.model.entity.rule;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.enums.PatentScope;
import top.alvinsite.demo.model.enums.PatentType;

import javax.validation.constraints.NotNull;

/**
 * @author Alvin
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("PatentRule")
public class PatentRule extends BaseRuleEntity  {

    @NotNull(message = "专利类型不能为空")
    private PatentType type;

    @NotNull(message = "专利范围不能为空")
    private PatentScope scope;

    @NotNull(message = "绩效分数不能为空")
    private Float score;
}
