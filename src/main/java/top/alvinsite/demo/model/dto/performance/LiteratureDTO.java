package top.alvinsite.demo.model.dto.performance;

import lombok.Data;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.enums.BookType;
import top.alvinsite.demo.model.support.ExcelColumn;

import java.util.List;

@Data
public class LiteratureDTO {
    private String id;

    @ExcelColumn(value = "姓名", col = 1)
    private String nickname;

    @ExcelColumn(value = "所属单位", col = 2)
    private String department;

    @ExcelColumn(value = "著作名称", col = 3)
    private String title;

    @ExcelColumn(value = "著作类别", col = 4)
    private String type;

    @ExcelColumn(value = "参编作者", col = 5)
    private List<ManagerUserDTO> authors;

    @ExcelColumn(value = "第一作者", col = 6)
    private String firstAuthor;

    @ExcelColumn(value = "总字数/万字", col = 7)
    private int wordCount;

    @ExcelColumn(value = "绩效分数", col = 8)
    private float score;
}
