package top.alvinsite.demo.model.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LevelFactorParam {
    private String type;
    private String level;

    @Override
    public String toString() {
        return String.format("级差系数（人员类别：%s，级别/职务：%s）", type, level);
    }
}
