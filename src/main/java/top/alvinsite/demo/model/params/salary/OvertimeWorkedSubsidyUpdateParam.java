package top.alvinsite.demo.model.params.salary;

import lombok.Data;
import top.alvinsite.demo.model.support.Excel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Alvin
 */
@Data
public class OvertimeWorkedSubsidyUpdateParam {

    private String id;

    private Integer year;

    @Excel(name = "姓名", col = 1, width = 10)
    private String nickname;

    @Excel(name = "工号", col = 2, width = 10)
    private String account;

    @NotBlank(message = "专业技术职务不能为空")
    @Excel(name = "专业技术职务", col = 3)
    private String professionalPosition;

    @NotNull(message = "实际完成课程教学工作量不能为空")
    @Excel(name = "实际完成课程教学工作量", col = 4)
    private Integer teachingWorkload;
}
