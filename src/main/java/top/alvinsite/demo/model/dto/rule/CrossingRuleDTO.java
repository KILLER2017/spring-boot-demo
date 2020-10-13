package top.alvinsite.demo.model.dto.rule;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("CrossingRuleDTO")
public class CrossingRuleDTO extends BaseRuleDTO {
    private Float min;
    private Float max;
    private Float score;
}
