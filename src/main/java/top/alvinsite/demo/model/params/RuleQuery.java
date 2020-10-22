package top.alvinsite.demo.model.params;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Alias("RuleQuery")
public class RuleQuery {
    @NotBlank(message = "单位不能为空")
    private String department;

    @NotNull(message = "年份不能为空")
    private Integer year;
}
