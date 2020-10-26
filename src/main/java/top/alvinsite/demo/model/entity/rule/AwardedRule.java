package top.alvinsite.demo.model.entity.rule;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.enums.HonorGrade;
import top.alvinsite.demo.model.enums.HonorLevel;

/**
 * @author Alvin
 */
@Data
@Alias("AwardedRule")
public class AwardedRule extends BaseRuleEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String department;
    private Integer year;
    private HonorLevel level;
    private HonorGrade grade;
    private Float score;

}
