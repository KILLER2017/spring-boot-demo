package top.alvinsite.demo.model.entity.rule;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @author Alvin
 */
@Data
@Alias("ScoreDistribution")
public class ScoreDistribution {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer year;
    private Integer totals;
    private Integer position;
    private Float proportion;
}
