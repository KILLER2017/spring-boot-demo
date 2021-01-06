package top.alvinsite.demo.model.entity.salary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.support.Excel;

/**
 * @author Alvin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Alias("JobSubsidy")
@NoArgsConstructor
@AllArgsConstructor
public class JobSubsidy extends BaseSalaryModel{

    @Excel(name = "岗位", col = 4)
    private String job;

    @Excel(name = "考核结果", col = 5, combo = {"优秀", "良好", "合格", "不合格"})
    private String evaluationResult;

    @Excel(name = "岗位津贴/月", col = 6)
    private Double subsidyFactor;

    private final Integer length = 10;

    @Excel(name = "个人岗位津贴/元", col = 7)
    private Double subsidy;

    @Excel(name = "备注", col = 8)
    private String remarks;
}
