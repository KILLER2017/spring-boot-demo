package top.alvinsite.demo.model.params.salary;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.alvinsite.demo.model.support.Excel;

import javax.validation.constraints.NotNull;

/**
 * @author Alvin
 */
@Data
@EqualsAndHashCode
public class WorkloadTargetUpdateParam {
    @NotNull(message = "ID不能为空")
    private String id;

    @Excel(name = "类型岗位", col = 1, combo = {"教学科研型岗", "公共课专任型岗", "科研教学型岗", "非中层干部型岗", "管理型岗", "实验教辅型岗", "实验教研型岗"})
    private String type;

    @Excel(name = "级别/职务", col = 2)
    private String level;

    @NotNull(message = "年度目标教学工作量不能为空")
    @Excel(name = "年度目标教学工作量", col = 3)
    private double teachingWorkloadTarget;

    @NotNull(message = "年度目标实验工作量不能为空")
    @Excel(name = "年度目标实验工作量", col = 4)
    private double experimentalTeachingWorkloadTarget;

    @NotNull(message = "年度目标科研工作量不能为空")
    @Excel(name = "年度目标科研工作量", col = 5)
    private double researchWorkloadTarget;
}
