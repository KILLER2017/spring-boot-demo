package top.alvinsite.demo.model.dto.performance;

import lombok.Data;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.support.Excel;

import java.util.List;

@Data
public class PaperDTO {
    private String id;

    @Excel(name = "姓名", col = 1)
    private String nickname;

    @Excel(name = "所属单位", col = 2)
    private String department;

    @Excel(name = "论文题目", col = 3)
    private String title;

    @Excel(name = "发表期刊", col = 4)
    private String periodical;

    @Excel(name = "刊物类型", col = 5)
    private String type;

    @Excel(name = "所有作者", col = 6)
    private List<ManagerUserDTO> authors;

    @Excel(name = "绩效分数", col = 7)
    private float score;
}
