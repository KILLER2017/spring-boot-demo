package top.alvinsite.demo.model.vo.salary;

import lombok.Data;

/**
 * 人员列表视图对象
 * @author Alvin
 */
@Data
public class PeopleListVo {
    /**
     * 姓名
     */
    private String nickname;

    /**
     * 业绩绩效工资
     */
    private double performanceWage;

    /**
     * 激励绩效工资
     */
    private double incentiveWage;

    /**
     * 超课时津贴
     */
    private double overtimeSubsidy;

    /**
     * 岗位津贴
     */
    private double jobSubsidy;

    /**
     * 双肩挑
     */
    private double specialSubsidy;

    /**
     * 奖励性绩效工资
     */
    private double totalSalary;
}
