package top.alvinsite.demo.model.params.salary;

import lombok.Data;
import top.alvinsite.demo.model.support.ExcelColumn;

import javax.validation.constraints.NotNull;

/**
 * @author Alvin
 */
@Data
public class WorkloadTargetUpdateParam {
    @NotNull(message = "ID不能为空")
    private Integer id;

    @ExcelColumn(value = "类型岗位", col = 1)
    private String type;

    @ExcelColumn(value = "级别/职务", col = 2)
    private String level;

    @NotNull(message = "年度目标教学工作量不能为空")
    @ExcelColumn(value = "年度目标教学工作量", col = 3)
    private double teachingWorkloadTarget;

    @NotNull(message = "年度目标实验工作量不能为空")
    @ExcelColumn(value = "年度目标实验工作量", col = 4)
    private double experimentalTeachingWorkloadTarget;

    @NotNull(message = "年度目标科研工作量不能为空")
    @ExcelColumn(value = "年度目标科研工作量", col = 5)
    private double researchWorkloadTarget;
}
