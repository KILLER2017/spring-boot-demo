package top.alvinsite.demo.model.entity.rule;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.enums.CopyrightType;

import javax.validation.constraints.NotNull;

/**
 * @author Alvin
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("CopyrightRule")
public class CopyrightRule extends BaseRuleEntity  {

    @NotNull(message = "著作权类型不能为空")
    private CopyrightType type;

    private Float score;
}
