package top.alvinsite.demo.model.entity.salary;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import top.alvinsite.demo.model.support.ExcelColumn;

/**
 * 超课时津贴实体类
 * @author Alvin
 */
@Data
public class OvertimeWorkedSubsidy {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer year;

    @ExcelColumn(value = "姓名", col = 1)
    private String nickname;

    @ExcelColumn(value = "工号", col = 2)
    private String account;

    @ExcelColumn(value = "专业技术职务", col = 3)
    private String professionalPosition;

    @ExcelColumn(value = "实际完成课程教学工作量", col = 4)
    private Integer teachingWorkload;
}
