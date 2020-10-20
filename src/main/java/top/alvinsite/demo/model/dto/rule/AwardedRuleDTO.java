package top.alvinsite.demo.model.dto.rule;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Data
@Alias("AwardedRuleDTO")
public class AwardedRuleDTO extends BaseRuleDTO {
    private String level;
    private String grade;
    private Float score;

}
