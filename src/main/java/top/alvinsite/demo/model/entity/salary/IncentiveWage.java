package top.alvinsite.demo.model.entity.salary;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import top.alvinsite.demo.model.support.ExcelColumn;

/**
 * 激励绩效工资实体类
 * @author Alvin
 */
@Data
public class IncentiveWage {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(exist = false)
    @ExcelColumn(value = "考核结果", col = 1)
    private String nickname;

    @ExcelColumn(value = "考核结果", col = 2)
    private String account;

    @ExcelColumn(value = "考核结果", col = 3)
    private Double incentivePerformanceScore;

    private Double incentivePerformanceSalary;
}
