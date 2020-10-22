package top.alvinsite.demo.model.entity.salary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Alias("Rule")
@AllArgsConstructor
@NoArgsConstructor
public class Rule {
    private Integer id;

    @NotNull
    private Integer year;

    @NotBlank(message = "岗位不能为空")
    private String type;

    @NotBlank(message = "类型岗位不能为空")
    private String postType;

    private String alias;

    @NotBlank(message = "计算规则不能为空")
    private String rule;
}
