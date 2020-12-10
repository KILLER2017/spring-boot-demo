package top.alvinsite.demo.model.entity.performance;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.entity.Department;
import top.alvinsite.demo.model.enums.PatentScope;
import top.alvinsite.demo.model.enums.PatentType;
import top.alvinsite.demo.model.support.ExcelColumn;

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
    private String id;

    @ExcelColumn(value = "姓名", col = 1)
    private String nickname;

    @ExcelColumn(value = "工号", col = 2)
    private String account;

    @ExcelColumn(value = "所属单位", col = 3)
    private Department department;

    @ExcelColumn(value = "专利名称", col = 4)
    private String title;

    @ExcelColumn(value = "专利范围", col = 5)
    private PatentScope scope;

    @ExcelColumn(value = "专利类型", col = 6)
    private PatentType type;

    @ExcelColumn(value = "公开时间", col = 7)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date publishTime;

    @ExcelColumn(value = "专利发明（设计）人", col = 8)
    private List<ManagerUserDTO> inventors;

    private Integer ApprovalProjectYear;

    private Integer memberNum;

    private Integer SignedOrder;

    @ExcelColumn(value = "绩效分数", col = 9)
    private float score;
}
