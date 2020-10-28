package top.alvinsite.demo.model.entity.rule;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String department;
    private Integer year;

    @NotNull(message = "是否东莞问题专著不能为空")
    @TableField(value = "is_topic_with_dongguan")
    private boolean TopicWithDongguan;

    @NotNull(message = "东莞问题专著绩效分数不能为空")
    private Float score;
}
