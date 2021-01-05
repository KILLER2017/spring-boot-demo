package top.alvinsite.demo.model.dto.performance;

import lombok.Data;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.support.Excel;

import java.util.List;

@Data
public class PatentDTO {
    private String id;

    @Excel(name = "姓名", col = 1)
    private String nickname;

    @Excel(name = "所属单位", col = 2)
    private String department;

    @Excel(name = "专利名称", col = 3)
    private String title;

    @Excel(name = "专利类型", col = 4)
    private String type;

    @Excel(name = "专利范围", col = 5)
    private String scope;

    @Excel(name = "专利发明（设计）人", col = 6)
    private List<ManagerUserDTO> inventors;

    @Excel(name = "绩效分数", col = 7)
    private float score;
}
