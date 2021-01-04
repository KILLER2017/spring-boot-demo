package top.alvinsite.demo.model.entity.salary;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.support.ExcelColumn;

/**
 * @author Alvin
 */
@Data
@Alias("SalarySummary")
@TableName("special_subsidy")
@EqualsAndHashCode(callSuper = true)
public class SalarySummary extends BaseSalaryModel {

    /**
     * 业绩绩效工资
     */
    @TableField(exist = false)
    @ExcelColumn(value = "业绩绩效工资", col = 4)
    private Double performanceWage;

    /**
     * 激励绩效工资
     */
    @TableField(exist = false)
    @ExcelColumn(value = "激励绩效工资", col = 5)
    private Double incentiveWage;

    /**
     * 超课时津贴
     */
    @TableField(exist = false)
    @ExcelColumn(value = "超课时津贴", col = 6)
    private Double overtimeSubsidy;

    /**
     * 岗位津贴
     */
    @TableField(exist = false)
    @ExcelColumn(value = "岗位津贴", col = 7)
    private Double jobSubsidy;

    /**
     * 双肩挑
     */
    @ExcelColumn(value = "双肩挑", col = 8)
    private Double specialSubsidy;

    /**
     * 奖励性绩效工资
     */
    @TableField(exist = false)
    @ExcelColumn(value = "奖励性绩效工资", col = 9)
    private Double totalSalary;
}
