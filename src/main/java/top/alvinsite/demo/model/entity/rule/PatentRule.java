package top.alvinsite.demo.model.entity.rule;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.enums.PatentScope;
import top.alvinsite.demo.model.enums.PatentType;

/**
 * @author Alvin
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("PatentRule")
public class PatentRule extends BaseRuleEntity  {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String department;
    private Integer year;
    private PatentType type;
    private PatentScope scope;
    private Float score;
}
