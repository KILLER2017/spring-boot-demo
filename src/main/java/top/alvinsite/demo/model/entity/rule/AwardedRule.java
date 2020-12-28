package top.alvinsite.demo.model.entity.rule;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.enums.HonorGrade;
import top.alvinsite.demo.model.enums.HonorLevel;

/**
 * @author Alvin
 */
@Data
@Alias("AwardedRule")
public class AwardedRule extends BaseRuleEntity {

    private HonorLevel level;
    private HonorGrade grade;
    private Float score;

}
