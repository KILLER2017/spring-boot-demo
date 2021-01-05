package top.alvinsite.demo.model.params.salary;

import lombok.Data;
import top.alvinsite.demo.model.support.ExcelColumn;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Alvin
 */
@Data
public class JobSubsidyUpdateParam {

    private Integer id;

    @NotNull(message = "年份不能为空")
    private Integer year;

    @ExcelColumn(value = "姓名", col = 1)
    private String nickname;

    @NotBlank(message = "工号不能为空")
    @ExcelColumn(value = "工号", col = 2)
    private String account;

    @NotBlank(message = "岗位不能为空")
    @ExcelColumn(value = "岗位", col = 3)
    private String job;

    @NotBlank(message = "考核结果不能为空")
    @ExcelColumn(value = "考核结果", col = 4)
    private String evaluationResult;

    @NotNull(message = "岗位津贴不能为空")
    @ExcelColumn(value = "岗位津贴/月", col = 5)
    private Double subsidyFactor;

    @ExcelColumn(value = "备注", col = 6)
    private String remarks;
}
