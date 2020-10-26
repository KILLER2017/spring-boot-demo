package top.alvinsite.demo.model.entity.rule;

import lombok.Data;

/**
 * @author Alvin
 */
@Data
public class SequencePoints {
    private Integer id;
    private String ruleType;
    private Integer ruleId;
    private Integer order;
    private Float score;
}
