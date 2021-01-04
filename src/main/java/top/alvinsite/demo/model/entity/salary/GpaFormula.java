package top.alvinsite.demo.model.entity.salary;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Alvin
 */
@Data
@Alias("GpaFormula")
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "salary_rule")
public class GpaFormula {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @NotNull(message = "年份不能为空")
    private Integer year;

    @NotBlank(message = "部门不能为空")
    private String department;

    @NotBlank(message = "岗位不能为空")
    private String type;

    @NotBlank(message = "类型岗位不能为空")
    private String postType;

    private String duty;

    private String alias;

    @NotBlank(message = "计算规则不能为空")
    private String rule;
}
