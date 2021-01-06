package top.alvinsite.demo.model.entity.salary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Alvin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ClassFeesStandard extends BaseModel {

    /**
     * 年份
     */
    @NotNull(message = "年份不能为空")
    private Integer year;

    @NotBlank(message = "单位不能为空")
    private String department;

    /**
     * 职称级别
     */
    @NotBlank(message = "职称级别不能为空")
    private String technicalPostsLevel;

    /**
     * 每节课时费
     */
    @NotNull(message = "每节课课时费不能为空")
    private Double factor;

}
