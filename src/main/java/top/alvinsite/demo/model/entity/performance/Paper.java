package top.alvinsite.demo.model.entity.performance;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.entity.type.PaperType;
import top.alvinsite.demo.model.support.ExcelColumn;

import java.util.Date;
import java.util.List;

/**
 * @author Alvin
 */
@Data
@Alias("Paper")
@EqualsAndHashCode(callSuper = true)
public class Paper extends BaseEntity  {
    @ExcelColumn(value = "论文题目", col = 4)
    private String title;

    @ExcelColumn(value = "发表期刊", col = 5)
    private String periodical;

    @ExcelColumn(value = "刊物类型", col = 6)
    private List<PaperType> publicationType;

    @ExcelColumn(value = "发表时间", col = 7)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date publishTime;

    @ExcelColumn(value = "组成成员", col = 8)
    private List<ManagerUserDTO> authors;
}
