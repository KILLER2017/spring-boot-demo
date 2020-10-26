package top.alvinsite.demo.model.entity.rule;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

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

    @TableField(value = "is_topic_with_dongguan")
    private boolean TopicWithDongguan;
    private Float score;
}
