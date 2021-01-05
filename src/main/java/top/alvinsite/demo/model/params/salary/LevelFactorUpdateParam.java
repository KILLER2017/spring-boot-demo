package top.alvinsite.demo.model.params.salary;

import lombok.Data;
import top.alvinsite.demo.model.support.Excel;

/**
 * @author Alvin
 */
@Data
public class LevelFactorUpdateParam {

    private String id;

    @Excel(name = "人员类别", col = 1)
    private String type;

    @Excel(name = "级别/职务", col = 2)
    private String level;

    @Excel(name = "级差系数", col = 3)
    private Double factor;
}
