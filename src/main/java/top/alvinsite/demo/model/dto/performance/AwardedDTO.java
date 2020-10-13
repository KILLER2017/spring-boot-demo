package top.alvinsite.demo.model.dto.performance;

import lombok.Data;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.enums.HonorGrade;
import top.alvinsite.demo.model.support.ExcelColumn;

import java.util.List;

@Data
public class AwardedDTO {
    protected String id;

    @ExcelColumn(value = "姓名", col = 1)
    protected String nickname;

    @ExcelColumn(value = "所属单位", col = 2)
    protected String department;

    @ExcelColumn(value = "奖励名称", col = 3)
    protected String title;

    @ExcelColumn(value = "获奖级别", col = 4)
    private String level;

    @ExcelColumn(value = "获奖等级", col = 5)
    private String grade;

    @ExcelColumn(value = "获奖完成人", col = 6)
    private List<ManagerUserDTO> awardee;

    @ExcelColumn(value = "绩效分数", col = 7)
    protected float score;

}
