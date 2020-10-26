package top.alvinsite.demo.model.entity.rule;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Alvin
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("PaperRule")
public class PaperRule extends BaseRuleEntity  {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String department;
    private Integer year;

    @NotBlank(message = "刊物类型不能为空")
    private String type;

    @NotNull(message = "绩效分数不能为空")
    private Float score;
}
