package top.alvinsite.demo.model.params;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RuleQuery {
    @NotBlank(message = "单位不能为空")
    private String department;

    @NotNull(message = "年份不能为空")
    @Range(min = 1993, max = 2040)
    private Integer year;
}
