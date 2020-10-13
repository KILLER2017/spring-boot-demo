package top.alvinsite.demo.model.dto.rule;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Data
@Alias("LiteratureRuleDTO")
public class LiteratureRuleDTO extends BaseRuleDTO{
    private String type;
    private Integer min;
    private Integer max;
    private Float score1;
    private Float score2;
    private Float score3;
    private Float score4;
    private Float score5;
    private Float score6;
    private Float score7;
    private Float score8;
}
