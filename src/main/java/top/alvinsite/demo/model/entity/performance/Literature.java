package top.alvinsite.demo.model.entity.performance;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.entity.Department;
import top.alvinsite.demo.model.enums.BookType;
import top.alvinsite.demo.model.enums.PublisherLevel;
import top.alvinsite.demo.model.enums.SubsidizeFrom;
import top.alvinsite.demo.model.support.ExcelColumn;

import java.util.List;

@Data
@Alias("Literature")
@EqualsAndHashCode(callSuper = false)
public class Literature extends BaseEntity  {
    private String id;

    @ExcelColumn(value = "姓名", col = 1)
    private String nickname;

    @ExcelColumn(value = "所属单位", col = 2)
    private Department department;

    @ExcelColumn(value = "著作名称", col = 3)
    private String title;

    @ExcelColumn(value = "著作类别", col = 4)
    private BookType type;

    // 出版社级别
    @TableField(value = "cbsjb")
    private PublisherLevel publisherLevel;

    // 资助来源
    @TableField(value = "zzlz")
    private SubsidizeFrom fundingSource;

    // 是否研究东莞问题专著
    private boolean isTopicWithDongguan = false;

    // 是否为修订版
    @TableField(value = "sfwxgb")
    private boolean isRevised;

    @ExcelColumn(value = "参编作者", col = 5)
    private List<ManagerUserDTO> authors;

    @ExcelColumn(value = "第一作者", col = 6)
    private String firstAuthor;

    @ExcelColumn(value = "总字数/万字", col = 7)
    private int wordCount;

    private Integer ApprovalProjectYear;

    private Integer memberNum;

    private Integer SignedOrder;

    @ExcelColumn(value = "绩效分数", col = 8)
    private float score;
}
