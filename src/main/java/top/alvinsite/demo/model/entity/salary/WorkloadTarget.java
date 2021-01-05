package top.alvinsite.demo.model.entity.salary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.support.Excel;

/**
 * @author Alvin
 */
@Data
@Alias("WorkloadTarget")
@AllArgsConstructor
@NoArgsConstructor
public class WorkloadTarget extends BaseModel {

    private Integer year;

    private String department;

    @Excel(name = "类型岗位", col = 1)
    private String type;

    @Excel(name = "级别/职务", col = 2)
    private String level;

    @Excel(name = "年度目标教学工作量", col = 3)
    private double teachingWorkloadTarget;

    @Excel(name = "年度目标实验工作量", col = 4)
    private double experimentalTeachingWorkloadTarget;

    @Excel(name = "年度目标科研工作量", col = 5)
    private double researchWorkloadTarget;
}
