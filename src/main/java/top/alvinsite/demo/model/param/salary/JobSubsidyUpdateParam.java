package top.alvinsite.demo.model.param.salary;

import lombok.Data;
import top.alvinsite.demo.model.support.Excel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Alvin
 */
@Data
public class JobSubsidyUpdateParam {

    private String id;

    private Integer year;

    @Excel(name = "姓名", col = 1)
    private String nickname;

    @Excel(name = "工号", col = 2)
    private String account;

    @NotBlank(message = "岗位不能为空")
    @Excel(name = "岗位", col = 3, isMultiSelect = true, combo = {"班主任", "系正主任", "系副主任", "实验室郑主任", "实验室副主任", "学院党总支部正书记", "学院党总支部副书记", "课程组组长"})
    private String job;

    @NotBlank(message = "考核结果不能为空")
    @Excel(name = "考核结果", col = 4, combo = {"优秀", "良好", "合格", "不合格"})
    private String evaluationResult;

    @NotNull(message = "岗位津贴不能为空")
    @Excel(name = "岗位津贴/月", col = 5)
    private Double subsidyFactor;

    @Excel(name = "备注", col = 6)
    private String remarks;
}
