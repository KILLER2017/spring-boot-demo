package top.alvinsite.demo.model.params.salary;

import lombok.Data;
import top.alvinsite.demo.model.support.ExcelColumn;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Alvin
 */
@Data
public class IncentiveWageUpdateParam {

    private Integer id;

    @NotNull(message = "年份不能为空")
    private Integer year;

    @ExcelColumn(value = "姓名", col = 1)
    private String nickname;

    @NotBlank(message = "工号不能为空")
    @ExcelColumn(value = "工号", col = 2)
    private String account;

    @NotBlank(message = "个人激励绩效分值不能为空")
    @ExcelColumn(value = "个人激励绩效分值", col = 3)
    private Double incentivePerformanceScore;
}
