package top.alvinsite.demo.model.entity.rule;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.enums.PatentScope;
import top.alvinsite.demo.model.enums.PatentType;

/**
 * @author Alvin
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("PatentRule")
public class PatentRule extends BaseRuleEntity  {

    private PatentType type;

    private PatentScope scope;

    private Float score;
}
