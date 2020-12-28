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
@Alias("LiteratureRuleTopicWithDongguan")
public class LiteratureRuleTopicWithDongguan extends BaseRuleEntity  {

    @NotNull(message = "是否东莞问题专著不能为空")
    @TableField(value = "is_topic_with_dongguan")
    private boolean TopicWithDongguan;

    @NotNull(message = "东莞问题专著绩效分数不能为空")
    private Float score;
}
