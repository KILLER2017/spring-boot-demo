package top.alvinsite.demo.model.entity.rule;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("ScoreDistribution")
public class ScoreDistribution {
    private Long id;
    private Integer year;
    private Integer totals;
    private Integer position;
    private Float proportion;
}
