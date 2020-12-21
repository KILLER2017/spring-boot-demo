package top.alvinsite.demo.model.entity.performance;

import lombok.Data;
import top.alvinsite.demo.model.entity.Department;
import top.alvinsite.demo.model.support.ExcelColumn;

/**
 * @author Alvin
 */
@Data
public abstract class BaseEntity {

    private String id;

    @ExcelColumn(value = "姓名", col = 1)
    private String nickname;

    @ExcelColumn(value = "工号", col = 2)
    private String account;

    @ExcelColumn(value = "所属单位", col = 3)
    private Department department;

    protected Integer ApprovalProjectYear;

    protected Integer memberNum;

    protected Integer SignedOrder;

    @ExcelColumn(value = "科研分数", col = 20)
    protected float score;
}
