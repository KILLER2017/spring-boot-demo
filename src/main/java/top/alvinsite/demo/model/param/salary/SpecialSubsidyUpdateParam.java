package top.alvinsite.demo.model.param.salary;

import lombok.Data;
import top.alvinsite.demo.model.support.Excel;

/**
 * @author Alvin
 */
@Data
public class SpecialSubsidyUpdateParam {

    private String id;

    @Excel(name = "姓名", col = 1)
    private String nickname;

    @Excel(name = "工号", col = 2)
    private String account;

    /**
     * 双肩挑
     */
    @Excel(name = "双肩挑", col = 3)
    private Double specialSubsidy;
}
