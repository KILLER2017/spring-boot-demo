package top.alvinsite.demo.model.entity.performance;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.entity.Department;
import top.alvinsite.demo.model.enums.HonorGrade;
import top.alvinsite.demo.model.enums.HonorLevel;
import top.alvinsite.demo.model.support.ExcelColumn;

import java.util.Date;
import java.util.List;

/**
 * @author Alvin
 */
@Data
@Alias("Awarded")
@EqualsAndHashCode(callSuper = true)
public class Awarded extends BaseEntity {
    protected String id;



    @ExcelColumn(value = "姓名", col = 1)
    protected String nickname;

    @ExcelColumn(value = "工号", col = 2)
    private String account;

    @ExcelColumn(value = "所属单位", col = 3)
    protected Department department;

    @ExcelColumn(value = "奖励名称", col = 4)
    protected String title;

    @ExcelColumn(value = "获奖级别", col = 5)
    private HonorLevel level;

    @ExcelColumn(value = "获奖等级", col = 6)
    private HonorGrade grade;

    @ExcelColumn(value = "获奖时间", col = 7)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date acquisitionTime;

    @ExcelColumn(value = "获奖完成人", col = 8)
    private List<ManagerUserDTO> awardee;

    private Integer ApprovalProjectYear;

    private Integer memberNum;

    private Integer SignedOrder;

    @ExcelColumn(value = "绩效分数", col = 9)
    private float score;
}
