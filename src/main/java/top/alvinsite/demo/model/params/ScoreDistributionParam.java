package top.alvinsite.demo.model.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreDistributionParam {
    private String department;
    private String performance;
    private Integer year;
    private Integer totals;
    private Integer position;
}
