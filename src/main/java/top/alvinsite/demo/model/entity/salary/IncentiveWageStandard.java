package top.alvinsite.demo.model.entity.salary;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author Alvin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class IncentiveWageStandard extends BaseModel {
    @NotNull(message = "年份不能为空")
    private Integer year;

    @NotNull(message = "激励绩效因子不能为空")
    private Double factor;
}
