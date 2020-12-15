package top.alvinsite.demo.model.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@Alias("LevelFactorParam")
@NoArgsConstructor
@AllArgsConstructor
public class LevelFactorParam {
    private String department;
    private String type;
    private String level;

    @Override
    public String toString() {
        return String.format("级差系数（人员类别：%s，级别/职务：%s）", type, level);
    }
}
