package top.alvinsite.demo.model.params.salary;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Alvin
 */
@Data
public class WorkloadTargetUpdateParam {
    @NotNull
    private Integer id;

    @NotBlank(message = "年度目标教学工作量")
    private double teachingWorkloadTarget;

    @NotBlank(message = "年度目标实验工作量")
    private double experimentalTeachingWorkloadTarget;

    @NotBlank(message = "年度目标科研工作量")
    private double researchWorkloadTarget;
}
