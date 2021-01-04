package top.alvinsite.demo.model.entity.salary;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.alvinsite.demo.model.support.ExcelColumn;

/**
 * @author Alvin
 * @email 54304634@qq.com
 * @date 2021/1/4 11:35
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseSalaryModel extends BaseModel {

    @ExcelColumn(value = "年份", col = 1)
    private Integer year;

    @TableField(exist = false)
    @ExcelColumn(value = "姓名", col = 2)
    private String nickname;

    @ExcelColumn(value = "工号", col = 3)
    private String account;
}
