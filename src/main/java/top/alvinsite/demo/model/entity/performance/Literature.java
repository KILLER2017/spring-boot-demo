package top.alvinsite.demo.model.entity.performance;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.enums.BookType;
import top.alvinsite.demo.model.enums.PublisherLevel;
import top.alvinsite.demo.model.enums.SubsidizeFrom;
import top.alvinsite.demo.model.support.ExcelColumn;

import java.util.Date;
import java.util.List;

/**
 * @author Alvin
 */
@Data
@Alias("Literature")
@EqualsAndHashCode(callSuper = true)
public class Literature extends BaseEntity  {

    @ExcelColumn(value = "著作名称", col = 4)
    private String title;

    @ExcelColumn(value = "著作类别", col = 5)
    private BookType type;

    @ExcelColumn(value = "总字数/万字", col = 6)
    private int wordCount;

    @ExcelColumn(value = "出版社级别", col = 7)
    @TableField(value = "cbsjb")
    private PublisherLevel publisherLevel;

    @ExcelColumn(value = "资助来源", col = 8)
    @TableField(value = "zzlz")
    private SubsidizeFrom fundingSource;

    @ExcelColumn(value = "是否研究东莞问题专著", col = 9)
    private boolean isTopicWithDongguan = false;

    @ExcelColumn(value = "是否为修订版", col = 10)
    @TableField(value = "sfwxgb")
    private boolean isRevised;

    @ExcelColumn(value = "出版时间", col = 11)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date publishTime;

    @ExcelColumn(value = "组成成员", col = 12)
    private List<ManagerUserDTO> authors;

    private String firstAuthor;
}
