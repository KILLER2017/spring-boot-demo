package top.alvinsite.demo.model.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreDistributionParam {
    private Integer year;
    private Integer totals;
    private Integer position;
}
