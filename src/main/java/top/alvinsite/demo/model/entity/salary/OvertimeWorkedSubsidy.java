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
public class OvertimeWorkedSubsidy extends BaseModel {

    @ExcelColumn(value = "年份", col = 1)
    private Integer year;

    @ExcelColumn(value = "姓名", col = 2)
    private String nickname;

    @ExcelColumn(value = "工号", col = 3)
    private String account;

    @ExcelColumn(value = "专业技术职务", col = 4)
    private String professionalPosition;

    @ExcelColumn(value = "实际完成课程教学工作量", col = 5)
    private Integer teachingWorkload;

    @ExcelColumn(value = "年度目标教学工作量", col = 6)
    private Integer teachingWorkloadTarget;

    @ExcelColumn(value = "超课时津贴/元", col = 7)
    private Double overtimeSubsidy;
}
