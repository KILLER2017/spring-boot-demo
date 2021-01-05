package top.alvinsite.demo.model.params.salary;

import lombok.Data;
import top.alvinsite.demo.model.support.Excel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Alvin
 */
@Data
public class PerformanceWageUpdateParam {

    private String id;

    private Integer year;

    @Excel(name = "姓名", col = 1, width = 10)
    private String nickname;

    @Excel(name = "工号", col = 2, width = 10)
    private String account;

    @NotBlank(message = "人员类别不能为空")
    @Excel(name = "人员类别", col = 3, width = 10)
    private String type;

    @NotBlank(message = "级别/职务不能为空")
    @Excel(name = "级别/职务", col = 4, width = 10)
    private String level;

    @NotBlank(message = "系列岗位不能为空")
    @Excel(name = "系列岗位", col = 5, width = 10)
    private String post;

    @NotBlank(message = "类型岗位不能为空")
    @Excel(name = "类型岗位", col = 6, width = 10)
    private String postType;

    @Excel(name = "职责情况", col = 7, width = 10)
    private String duty;

    @NotNull(message = "个人实际民主测评值不能为空")
    @Excel(name = "个人实际民主测评值", col = 8)
    private Double measurement;

    @NotNull(message = "实际完成课程教学工作量不能为空")
    @Excel(name = "实际完成课程教学工作量", col = 8)
    private Integer teachingWorkload;

    @NotNull(message = "实际完成科研工作量不能为空")
    @Excel(name = "实际完成科研工作量", col = 10)
    private Integer researchWorkload;

    @NotNull(message = "实际完成教研教改工作量不能为空")
    @Excel(name = "实际完成教研教改工作量", col = 11)
    private Integer teachingResearchWorkload;

    @NotNull(message = "实际完成总实验教学工作量不能为空")
    @Excel(name = "实际完成总实验教学工作量", col = 12)
    private Integer experimentalTeachingWorkload;
}
