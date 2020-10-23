package top.alvinsite.demo.model.entity.performance;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.entity.Department;
import top.alvinsite.demo.model.enums.HonorGrade;
import top.alvinsite.demo.model.enums.HonorLevel;
import top.alvinsite.demo.model.support.ExcelColumn;

import java.util.List;

@Data
@Alias("Awarded")
@EqualsAndHashCode(callSuper = false)
public class Awarded extends BaseEntity {
    protected String id;

    private String account;

    @ExcelColumn(value = "姓名", col = 1)
    protected String nickname;

    @ExcelColumn(value = "所属单位", col = 2)
    protected Department department;

    @ExcelColumn(value = "奖励名称", col = 3)
    protected String title;

    @ExcelColumn(value = "获奖级别", col = 4)
    private HonorLevel level;

    @ExcelColumn(value = "获奖等级", col = 5)
    private HonorGrade grade;

    @ExcelColumn(value = "获奖完成人", col = 6)
    private List<ManagerUserDTO> awardee;


    private Integer ApprovalProjectYear;

    private Integer memberNum;

    private Integer SignedOrder;

    @ExcelColumn(value = "绩效分数", col = 7)
    private float score;
}
