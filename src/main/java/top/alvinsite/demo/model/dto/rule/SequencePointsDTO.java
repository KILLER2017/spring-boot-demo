package top.alvinsite.demo.model.dto.rule;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("SequencePointsDTO")
public class SequencePointsDTO {
    private Integer id;
    private String ruleType;
    private Integer ruleId;
    private Integer order;
    private Float score;
}
