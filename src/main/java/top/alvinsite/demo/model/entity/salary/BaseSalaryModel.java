package top.alvinsite.demo.model.entity.salary;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.alvinsite.demo.model.support.Excel;

/**
 * @author Alvin
 * @email 54304634@qq.com
 * @date 2021/1/4 11:35
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseSalaryModel extends BaseModel {

    @Excel(name = "年份", col = 1)
    private Integer year;

    @TableField(exist = false)
    @Excel(name = "姓名", col = 2)
    private String nickname;

    @Excel(name = "工号", col = 3)
    private String account;
}
