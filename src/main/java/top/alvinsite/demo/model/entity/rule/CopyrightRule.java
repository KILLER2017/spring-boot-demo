package top.alvinsite.demo.model.entity.rule;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.dto.rule.BaseRuleDTO;
import top.alvinsite.demo.model.enums.CopyrightType;

import javax.validation.constraints.NotNull;

@Data
@Alias("CopyrightRule")
public class CopyrightRule extends BaseRuleEntity  {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String department;

    private Integer year;

    @NotNull(message = "著作权类型不能为空")
    private CopyrightType type;

    private Float score;
}
