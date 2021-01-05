package top.alvinsite.demo.model.dto.performance;

import lombok.Data;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.support.Excel;

import java.util.List;

@Data
public class CopyrightDTO {
    private String id;

    @Excel(name = "姓名", col = 1)
    private String nickname;

    @Excel(name = "所属单位", col = 2)
    private String department;

    @Excel(name = "著作权名称", col = 3)
    private String title;

    @Excel(name = "著作权类型", col = 4)
    private String type;

    @Excel(name = "所有作者", col = 5)
    private List<ManagerUserDTO> authors;

    @Excel(name = "绩效分数", col = 6)
    private float score;
}
