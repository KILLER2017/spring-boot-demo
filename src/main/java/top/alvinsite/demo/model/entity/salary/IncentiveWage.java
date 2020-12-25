package top.alvinsite.demo.model.entity.salary;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.support.ExcelColumn;

/**
 * 激励绩效工资实体类
 * @author Alvin
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Alias("IncentiveWage")
public class IncentiveWage extends BaseModel {

    private Integer year;

    @TableField(exist = false)
    @ExcelColumn(value = "姓名", col = 1)
    private String nickname;

    @ExcelColumn(value = "工号", col = 2)
    private String account;

    @ExcelColumn(value = "个人激励绩效分值", col = 3)
    private Double incentivePerformanceScore;

    @TableField(exist = false)
    @ExcelColumn(value = "个人激励绩效分值", col = 4)
    private Double incentivePerformanceSalary;
}
