package top.alvinsite.demo.model.entity.salary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.support.Excel;

import java.util.Objects;

/**
 * @author Alvin
 */
@Data
@Alias("WorkloadTarget")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        WorkloadTarget that = (WorkloadTarget) o;
        return Double.compare(that.teachingWorkloadTarget, teachingWorkloadTarget) == 0 &&
                Double.compare(that.experimentalTeachingWorkloadTarget, experimentalTeachingWorkloadTarget) == 0 &&
                Double.compare(that.researchWorkloadTarget, researchWorkloadTarget) == 0 &&
                year.equals(that.year) &&
                department.equals(that.department) &&
                type.equals(that.type) &&
                level.equals(that.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), year, department, type, level, teachingWorkloadTarget, experimentalTeachingWorkloadTarget, researchWorkloadTarget);
    }
}
