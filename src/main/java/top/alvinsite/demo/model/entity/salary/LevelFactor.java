package top.alvinsite.demo.model.entity.salary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.support.Excel;

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

    @Excel(name = "人员类别", col = 1)
    private String type;

    @Excel(name = "级别/职务", col = 2)
    private String level;

    @Excel(name = "级差系数", col = 3)
    private Double factor;

}
