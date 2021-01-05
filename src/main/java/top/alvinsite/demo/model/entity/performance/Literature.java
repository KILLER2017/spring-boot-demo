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
import top.alvinsite.demo.model.support.Excel;

import java.util.Date;
import java.util.List;

/**
 * @author Alvin
 */
@Data
@Alias("Literature")
@EqualsAndHashCode(callSuper = true)
public class Literature extends BaseEntity  {

    @Excel(name = "著作名称", col = 4)
    private String title;

    @Excel(name = "著作类别", col = 5)
    private BookType type;

    @Excel(name = "总字数/万字", col = 6)
    private int wordCount;

    @Excel(name = "出版社级别", col = 7)
    @TableField(value = "cbsjb")
    private PublisherLevel publisherLevel;

    @Excel(name = "资助来源", col = 8)
    @TableField(value = "zzlz")
    private SubsidizeFrom fundingSource;

    @Excel(name = "是否研究东莞问题专著", col = 9)
    private boolean isTopicWithDongguan = false;

    @Excel(name = "是否为修订版", col = 10)
    @TableField(value = "sfwxgb")
    private boolean isRevised;

    @Excel(name = "出版时间", col = 11)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date publishTime;

    @Excel(name = "组成成员", col = 12)
    private List<ManagerUserDTO> authors;

    private String firstAuthor;
}
