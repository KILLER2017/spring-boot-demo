package top.alvinsite.demo.model.params.salary;

import lombok.Data;
import top.alvinsite.demo.model.support.ExcelColumn;

/**
 * @author Alvin
 */
@Data
public class PerformanceWageUpdateParam {

    private Integer year;

    @ExcelColumn(value = "姓名", col = 1, width = 10)
    private String nickname;

    @ExcelColumn(value = "工号", col = 2, width = 10)
    private String account;

    @ExcelColumn(value = "人员类别", col = 3, width = 10)
    private String type;

    @ExcelColumn(value = "级别/职务", col = 4, width = 10)
    private String level;

    @ExcelColumn(value = "系列岗位", col = 5, width = 10)
    private String post;

    @ExcelColumn(value = "类型岗位", col = 6, width = 10)
    private String postType;

    @ExcelColumn(value = "职责情况", col = 7, width = 10)
    private String duty;

    @ExcelColumn(value = "个人实际民主测评值", col = 8)
    private double measurement;

    @ExcelColumn(value = "实际完成课程教学工作量", col = 8)
    private Integer teachingWorkload;

    @ExcelColumn(value = "实际完成科研工作量", col = 10)
    private Integer researchWorkload;

    @ExcelColumn(value = "实际完成教研教改工作量", col = 11)
    private Integer teachingResearchWorkload;

    @ExcelColumn(value = "实际完成总实验教学工作量", col = 12)
    private Integer experimentalTeachingWorkload;
}
