package top.alvinsite.demo.model.entity.rule;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.dto.rule.BaseRuleDTO;
import top.alvinsite.demo.model.enums.PatentScope;
import top.alvinsite.demo.model.enums.PatentType;

@Data
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
