package top.alvinsite.demo.model.entity.performance;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.enums.CopyrightType;
import top.alvinsite.demo.model.support.ExcelColumn;

import java.util.Date;
import java.util.List;

/**
 * @author Alvin
 */
@Data
@Alias("Copyright")
@EqualsAndHashCode(callSuper = true)
public class Copyright extends BaseEntity  {

    @ExcelColumn(value = "著作权名称", col = 3)
    private String title;

    @ExcelColumn(value = "著作权类型", col = 4)
    private CopyrightType type;

    @ExcelColumn(value = "出版时间", col = 5)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date publishTime;

    @ExcelColumn(value = "所有作者", col = 6)
    private List<ManagerUserDTO> authors;
}
