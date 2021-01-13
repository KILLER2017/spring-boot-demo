package top.alvinsite.demo.model.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@Alias("SalaryRuleParam")
@NoArgsConstructor
@AllArgsConstructor
public class SalaryRuleParam {
    private String department;
    private int year;
    private String post;
    private String postType;
    private String duty;
}
