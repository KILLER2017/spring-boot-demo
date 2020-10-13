package top.alvinsite.demo.model.dto.performance;

import lombok.Data;
import top.alvinsite.demo.model.support.ExcelColumn;

@Data
public class BaseProjectDTO {
    protected String id;

    @ExcelColumn(value = "姓名", col = 1)
    protected String nickname;

    @ExcelColumn(value = "所属单位", col = 2)
    protected String department;

    @ExcelColumn(value = "项目名称", col = 3)
    protected String title;

    @ExcelColumn(value = "绩效分数", col = 4)
    protected float score;
}
