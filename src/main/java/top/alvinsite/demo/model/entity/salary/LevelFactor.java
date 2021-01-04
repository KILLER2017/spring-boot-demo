package top.alvinsite.demo.model.entity.salary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.support.ExcelColumn;

/**
 * @author Alvin
 */
@Data
@Alias("LevelFactor")
@AllArgsConstructor
@NoArgsConstructor
public class LevelFactor extends BaseModel{

    private Integer year;

    private String department;

    @ExcelColumn(value = "人员类别", col = 1)
    private String type;

    @ExcelColumn(value = "级别/职务", col = 2)
    private String level;

    @ExcelColumn(value = "级差系数", col = 3)
    private Double factor;

}
