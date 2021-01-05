package top.alvinsite.demo.model.entity.salary;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.support.Excel;

/**
 * 激励绩效工资实体类
 * @author Alvin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Alias("IncentiveWage")
public class IncentiveWage extends BaseSalaryModel {

    @Excel(name = "个人激励绩效分值", col = 4)
    private Double incentivePerformanceScore;

    @TableField(exist = false)
    @Excel(name = "个人激励绩效分值", col = 5)
    private Double incentivePerformanceSalary;
}
