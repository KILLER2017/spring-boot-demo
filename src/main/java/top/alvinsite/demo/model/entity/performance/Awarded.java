package top.alvinsite.demo.model.entity.performance;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.enums.HonorGrade;
import top.alvinsite.demo.model.enums.HonorLevel;
import top.alvinsite.demo.model.support.ExcelColumn;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Alvin
 */
@Data
@Alias("Awarded")
@EqualsAndHashCode(callSuper = true)
public class Awarded extends BaseEntity {

    @ExcelColumn(value = "奖励名称", col = 4)
    protected String title;

    @ExcelColumn(value = "获奖级别", col = 5)
    private HonorLevel level;

    @ExcelColumn(value = "获奖等级", col = 6)
    private HonorGrade grade;

    @ExcelColumn(value = "获奖时间", col = 7)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate acquisitionTime;

    @ExcelColumn(value = "获奖完成人", col = 8)
    private List<ManagerUserDTO> awardee;
}
