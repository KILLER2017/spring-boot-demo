package top.alvinsite.demo.model.entity.salary;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.support.ExcelColumn;

/**
 * 超课时津贴实体类
 * @author Alvin
 */
@Data
@Alias("OvertimeWorkedSubsidy")
@EqualsAndHashCode(callSuper = true)
public class OvertimeWorkedSubsidy extends BaseSalaryModel {

    @ExcelColumn(value = "专业技术职务", col = 4)
    private String professionalPosition;

    @ExcelColumn(value = "实际完成课程教学工作量", col = 5)
    private Integer teachingWorkload;

    @ExcelColumn(value = "年度目标教学工作量", col = 6)
    private Integer teachingWorkloadTarget;

    @ExcelColumn(value = "超课时津贴/元", col = 7)
    private Double overtimeSubsidy;
}
