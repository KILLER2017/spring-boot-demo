package top.alvinsite.demo.model.entity.rule;

import lombok.Data;

@Data
public class SequencePoints {
    private Integer id;
    private String ruleType;
    private Integer ruleId;
    private Integer order;
    private Float score;
}
