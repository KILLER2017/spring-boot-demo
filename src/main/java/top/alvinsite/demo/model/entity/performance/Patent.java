package top.alvinsite.demo.model.entity.performance;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.enums.PatentScope;
import top.alvinsite.demo.model.enums.PatentType;
import top.alvinsite.demo.model.support.Excel;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Alvin
 */
@Data
@Alias("Patent")
@EqualsAndHashCode(callSuper = true)
public class Patent extends BaseEntity {

    @Excel(name = "专利名称", col = 4)
    private String title;

    @Excel(name = "专利范围", col = 5)
    private PatentScope scope;

    @Excel(name = "专利类型", col = 6)
    private PatentType type;

    @Excel(name = "公开时间", col = 7)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date publishTime;

    @Excel(name = "专利发明（设计）人", col = 8)
    private List<ManagerUserDTO> inventors;

}
