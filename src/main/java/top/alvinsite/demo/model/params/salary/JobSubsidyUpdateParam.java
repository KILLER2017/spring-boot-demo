package top.alvinsite.demo.model.params.salary;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Alvin
 */
@Data
public class JobSubsidyUpdateParam {
    @NotNull
    private Integer id;

    @NotBlank(message = "岗位不能为空")
    private String job;

    @NotBlank(message = "考核结果不能为空")
    private String evaluationResult;

    @NotNull(message = "岗位津贴不能为空")
    private Double subsidyFactor;

    private String remarks;
}
