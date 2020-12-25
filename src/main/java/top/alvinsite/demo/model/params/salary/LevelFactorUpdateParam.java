package top.alvinsite.demo.model.params.salary;

import lombok.Data;
import top.alvinsite.demo.model.support.ExcelColumn;

/**
 * @author Alvin
 */
@Data
public class LevelFactorUpdateParam {

    private Integer id;

    @ExcelColumn(value = "人员类别", col = 1)
    private String type;

    @ExcelColumn(value = "级别/职务", col = 2)
    private String level;

    @ExcelColumn(value = "级差系数", col = 3)
    private Double factor;
}
