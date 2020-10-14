package top.alvinsite.demo.model.entity.rule;

import lombok.Data;

import java.util.List;

@Data
public class PaperRule {
    private Integer id;
    private String department;
    private Integer year;
    private String type;
    private Float score;
}
