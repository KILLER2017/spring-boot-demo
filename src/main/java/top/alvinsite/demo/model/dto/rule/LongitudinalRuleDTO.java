package top.alvinsite.demo.model.dto.rule;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("LongitudinalRuleDTO")
public class LongitudinalRuleDTO extends BaseRuleDTO {

    private String type;
    private String level;
    private Integer min;
    private Integer max;
    private Float score;
}
