package top.alvinsite.demo.model.entity.rule;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("ScoreDistributionConfig")
public class ScoreDistributionConfig {
    private Long id;
    private Integer year;
    private String department;
    private String performance;

    @TableField(value = "is_active")
    private Boolean active;

}
