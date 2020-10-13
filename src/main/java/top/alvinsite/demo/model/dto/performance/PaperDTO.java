package top.alvinsite.demo.model.dto.performance;

import lombok.Data;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.support.ExcelColumn;

import java.util.List;

@Data
public class PaperDTO {
    private String id;

    @ExcelColumn(value = "姓名", col = 1)
    private String nickname;

    @ExcelColumn(value = "所属单位", col = 2)
    private String department;

    @ExcelColumn(value = "论文题目", col = 3)
    private String title;

    @ExcelColumn(value = "发表期刊", col = 4)
    private String periodical;

    @ExcelColumn(value = "刊物类型", col = 5)
    private String type;

    @ExcelColumn(value = "所有作者", col = 6)
    private List<ManagerUserDTO> authors;

    @ExcelColumn(value = "绩效分数", col = 7)
    private float score;
}
