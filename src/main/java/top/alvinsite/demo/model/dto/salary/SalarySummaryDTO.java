package top.alvinsite.demo.model.dto.salary;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("SalarySummaryDTO")
public class SalarySummaryDTO {
    private Integer year;
    private String account;
    private String nickname;
    private String type = "管理人员";
    private String level = "二级(正高)";
    private String post = "教师";
    private String postType = "教学科研岗";
    private String duty;
    private int teachingWorkload = 120;
    private int researchWorkload = 120;
    private double performanceWage;             // 业绩绩效工资
    private double incentiveWage = 100.00f;     // 激励绩效工资
    private double overtimeSubsidy = 100.00f;   // 超课时津贴
    private double jobSubsidy = 100.00f;        // 岗位津贴
    private double specialSubsidy = 100.00f;    // 双肩挑
    private double totalSalary;                 // 奖励性绩效工资
    private double measurement;
}
