package top.alvinsite.demo.model.params.salary;

import lombok.Data;
import top.alvinsite.demo.model.support.ExcelColumn;

/**
 * @author Alvin
 */
@Data
public class SpecialSubsidyUpdateParam {

    private Integer id;

    @ExcelColumn(value = "姓名", col = 1)
    private String nickname;

    @ExcelColumn(value = "工号", col = 2)
    private String account;

    /**
     * 双肩挑
     */
    @ExcelColumn(value = "双肩挑", col = 3)
    private Double specialSubsidy;
}
