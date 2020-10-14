package top.alvinsite.demo.model.entity.performance;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.support.ExcelColumn;

import java.util.List;

@Data
@Alias("Patent")
public class Patent {
    private String id;

    @ExcelColumn(value = "姓名", col = 1)
    private String nickname;

    @ExcelColumn(value = "所属单位", col = 2)
    private String department;

    @ExcelColumn(value = "专利名称", col = 3)
    private String title;

    @ExcelColumn(value = "专利类型", col = 4)
    private String type;

    @ExcelColumn(value = "专利范围", col = 5)
    private String scope;

    @ExcelColumn(value = "专利发明（设计）人", col = 6)
    private List<ManagerUserDTO> inventors;

    @ExcelColumn(value = "绩效分数", col = 7)
    private float score;
}
