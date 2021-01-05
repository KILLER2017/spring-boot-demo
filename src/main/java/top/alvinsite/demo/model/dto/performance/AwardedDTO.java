package top.alvinsite.demo.model.dto.performance;

import lombok.Data;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.support.Excel;

import java.util.List;

@Data
public class AwardedDTO {
    protected String id;

    @Excel(name = "姓名", col = 1)
    protected String nickname;

    @Excel(name = "所属单位", col = 2)
    protected String department;

    @Excel(name = "奖励名称", col = 3)
    protected String title;

    @Excel(name = "获奖级别", col = 4)
    private String level;

    @Excel(name = "获奖等级", col = 5)
    private String grade;

    @Excel(name = "获奖完成人", col = 6)
    private List<ManagerUserDTO> awardee;

    @Excel(name = "绩效分数", col = 7)
    protected float score;

}
