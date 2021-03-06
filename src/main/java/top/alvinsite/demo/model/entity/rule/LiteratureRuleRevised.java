package top.alvinsite.demo.model.entity.rule;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotNull;

/**
 * @author Alvin
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("LiteratureRuleRevised")
public class LiteratureRuleRevised extends BaseRuleEntity  {

    @NotNull(message = "是否修订版本不能为空")
    @TableField(value = "is_revised")
    private boolean revised;

    @NotNull(message = "是否修订版本绩效分数不能为空")
    private Float score;
}
