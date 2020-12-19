package top.alvinsite.demo.model.entity.salary;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.support.ExcelColumn;

import java.time.LocalDateTime;

/**
 * @author Alvin
 */
@Data
@Alias("JobSubsidy")
@NoArgsConstructor
@AllArgsConstructor
public class JobSubsidy {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer year;

    @TableField(exist = false)
    @ExcelColumn(value = "姓名", col = 1)
    private String nickname;

    @ExcelColumn(value = "工号", col = 2)
    private String account;

    @ExcelColumn(value = "岗位", col = 3)
    private String job;

    @ExcelColumn(value = "考核结果", col = 4)
    private String evaluationResult;

    @ExcelColumn(value = "岗位津贴/月", col = 5)
    private Double subsidyFactor;

    private final Integer length = 10;

    private Double subsidy;

    @ExcelColumn(value = "备注", col = 6)
    private String remarks;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
