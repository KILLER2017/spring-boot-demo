package top.alvinsite.demo.model.params.salary;

import lombok.Data;
import top.alvinsite.demo.model.support.Excel;

import javax.validation.constraints.NotNull;

/**
 * @author Alvin
 */
@Data
public class IncentiveWageUpdateParam {

    private String id;

    private Integer year;

    @Excel(name = "姓名", col = 1)
    private String nickname;

    @Excel(name = "工号", col = 2)
    private String account;

    @NotNull(message = "个人激励绩效分值不能为空")
    @Excel(name = "个人激励绩效分值", col = 3)
    private Double incentivePerformanceScore;
}
