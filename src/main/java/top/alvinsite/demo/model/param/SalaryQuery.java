package top.alvinsite.demo.model.param;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotBlank;

/**
 * @author Alvin
 */
@Data
@Alias("SalaryQuery")
public class SalaryQuery {
    @NotBlank(message = "单位不能为空")
    private String department;
}
