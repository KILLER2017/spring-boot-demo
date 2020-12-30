package top.alvinsite.demo.model.params.salary;

import lombok.Data;
import top.alvinsite.demo.model.support.ExcelColumn;

/**
 * @author Alvin
 */
@Data
public class OvertimeWorkedSubsidyUpdateParam {

    private Integer id;

    @ExcelColumn(value = "姓名", col = 1, width = 10)
    private String nickname;

    @ExcelColumn(value = "工号", col = 2, width = 10)
    private String account;

    @ExcelColumn(value = "专业技术职务", col = 3)
    private String professionalPosition;

    @ExcelColumn(value = "实际完成课程教学工作量", col = 4)
    private Integer teachingWorkload;
}
