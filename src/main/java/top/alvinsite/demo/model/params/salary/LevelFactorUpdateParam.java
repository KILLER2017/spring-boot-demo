package top.alvinsite.demo.model.params.salary;

import lombok.Data;
import top.alvinsite.demo.model.support.ExcelColumn;

/**
 * @author Alvin
 */
@Data
public class LevelFactorUpdateParam {

    private Integer id;

    @ExcelColumn(value = "级差系数", col = 3)
    private Double factor;
}
