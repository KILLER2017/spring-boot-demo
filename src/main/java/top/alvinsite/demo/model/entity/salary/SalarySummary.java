package top.alvinsite.demo.model.entity.salary;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.support.ExcelColumn;

/**
 * @author Alvin
 */
@Data
@Alias("SalarySummary")
public class SalarySummary {

    @TableId(type = IdType.AUTO)
    private String id;

    private Integer year;

    @ExcelColumn(value = "账号", col = 2, width = 15)
    private String account;

    @ExcelColumn(value = "姓名", col = 1, width = 15)
    private String nickname;

    private String department;

    @ExcelColumn(value = "人员类别", col = 3)
    private String type;

    @ExcelColumn(value = "级别/职务", col = 4)
    private String level;

    @ExcelColumn(value = "系列岗位", col = 5)
    private String post;

    @ExcelColumn(value = "类型岗位", col = 6)
    private String postType;

    @ExcelColumn(value = "职责情况", col = 7)
    private String duty;

    @ExcelColumn(value = "实际教学工作量", col = 8)
    private int teachingWorkload;

    @ExcelColumn(value = "实际科研工作量", col = 9)
    private int researchWorkload;

    private int teachingResearchWorkload;

    private int experimentalTeachingWorkload;

    /**
     * 业绩绩效工资
     */
    private double performanceWage;

    /**
     * 激励绩效工资
     */
    @ExcelColumn(value = "激励绩效工资", col = 10)
    private double incentiveWage;

    /**
     * 超课时津贴
     */
    @ExcelColumn(value = "超课时津贴", col = 11)
    private double overtimeSubsidy;

    /**
     * 岗位津贴
     */
    @ExcelColumn(value = "岗位津贴", col = 12)
    private double jobSubsidy;

    /**
     * 双肩挑
     */
    @ExcelColumn(value = "双肩挑", col = 13)
    private double specialSubsidy;

    /**
     * 奖励性绩效工资
     */
    @ExcelColumn(value = "奖励性绩效工资", col = 14)
    private double totalSalary;

    @ExcelColumn(value = "个人民主评测值", col = 15)
    private double measurement;
}
