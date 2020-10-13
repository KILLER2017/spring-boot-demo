package top.alvinsite.demo.model.dto.rule;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.entity.rule.SequencePoints;

import java.util.List;

@Data
@Alias("PatentRuleDTO")
public class PatentRuleDTO extends BaseRuleDTO {
    private String type;
    private String scope;
    private Float score1;
    private Float score2;
    private Float score3;
    private Float score4;
    private Float score5;
    private Float score6;
    private Float score7;
    private Float score8;
}
