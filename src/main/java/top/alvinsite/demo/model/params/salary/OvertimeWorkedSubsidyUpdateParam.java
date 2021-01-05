package top.alvinsite.demo.model.params.salary;

import lombok.Data;
import top.alvinsite.demo.model.support.ExcelColumn;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Alvin
 */
@Data
public class OvertimeWorkedSubsidyUpdateParam {

    private String id;

    private Integer year;

    @ExcelColumn(value = "姓名", col = 1, width = 10)
    private String nickname;

    @ExcelColumn(value = "工号", col = 2, width = 10)
    private String account;

    @NotBlank(message = "专业技术职务不能为空")
    @ExcelColumn(value = "专业技术职务", col = 3)
    private String professionalPosition;

    @NotNull(message = "实际完成课程教学工作量不能为空")
    @ExcelColumn(value = "实际完成课程教学工作量", col = 4)
    private Integer teachingWorkload;
}
