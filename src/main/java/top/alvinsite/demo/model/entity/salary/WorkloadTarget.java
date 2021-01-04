package top.alvinsite.demo.model.entity.salary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.support.ExcelColumn;

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

    @ExcelColumn(value = "类型岗位", col = 1)
    private String type;

    @ExcelColumn(value = "级别/职务", col = 2)
    private String level;

    @ExcelColumn(value = "年度目标教学工作量", col = 3)
    private double teachingWorkloadTarget;

    @ExcelColumn(value = "年度目标实验工作量", col = 4)
    private double experimentalTeachingWorkloadTarget;

    @ExcelColumn(value = "年度目标科研工作量", col = 5)
    private double researchWorkloadTarget;
}
