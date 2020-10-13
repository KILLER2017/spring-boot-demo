package top.alvinsite.demo.model.dto.rule;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Data
@Alias("CopyrightRuleDTO")
public class CopyrightRuleDTO extends BaseRuleDTO {
    private Float score1;
    private Float score2;
    private Float score3;
    private Float score4;
    private Float score5;
    private Float score6;
    private Float score7;
    private Float score8;
}
