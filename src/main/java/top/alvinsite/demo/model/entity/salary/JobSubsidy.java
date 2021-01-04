package top.alvinsite.demo.model.entity.salary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.support.ExcelColumn;

/**
 * @author Alvin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Alias("JobSubsidy")
@NoArgsConstructor
@AllArgsConstructor
public class JobSubsidy extends BaseSalaryModel{

    @ExcelColumn(value = "岗位", col = 4)
    private String job;

    @ExcelColumn(value = "考核结果", col = 5)
    private String evaluationResult;

    @ExcelColumn(value = "岗位津贴/月", col = 6)
    private Double subsidyFactor;

    private final Integer length = 10;

    @ExcelColumn(value = "个人岗位津贴/元", col = 7)
    private Double subsidy;

    @ExcelColumn(value = "备注", col = 8)
    private String remarks;
}
