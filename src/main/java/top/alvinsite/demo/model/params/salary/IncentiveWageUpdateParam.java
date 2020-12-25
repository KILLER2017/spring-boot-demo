package top.alvinsite.demo.model.params.salary;

import lombok.Data;
import top.alvinsite.demo.model.support.ExcelColumn;

/**
 * @author Alvin
 */
@Data
public class IncentiveWageUpdateParam {

    private Integer id;

    @ExcelColumn(value = "姓名", col = 1)
    private String nickname;

    @ExcelColumn(value = "工号", col = 2)
    private String account;

    @ExcelColumn(value = "个人激励绩效分值", col = 3)
    private Double incentivePerformanceScore;
}
