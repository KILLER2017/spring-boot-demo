package top.alvinsite.demo.model.entity.rule;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @author Alvin
 */
@Data
@Alias("ScoreDistributionConfig")
public class ScoreDistributionConfig {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer year;
    private String department;
    private String performance;

    @TableField(value = "is_active")
    private Boolean active;

}
