package top.alvinsite.demo.model.param;


import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotNull;

/**
 * @author Alvin
 */
@Data
@Alias("SalaryRuleQuery")
public class SalaryRuleQuery extends SalaryQuery {

    @NotNull(message = "年份不能为空")
    private Integer year;
}
