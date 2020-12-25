package top.alvinsite.demo.model.entity.salary;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Alvin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PerformanceWageFormula extends BaseModel {

    @NotNull(message = "年份不能为空")
    private Integer year;

    @NotBlank(message = "单位不能为空")
    private String department;

    private String alias;

    @NotBlank(message = "计算规则不能为空")
    private String formula;
}
