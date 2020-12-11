package top.alvinsite.demo.model.entity.performance;

import lombok.Data;
import top.alvinsite.demo.model.support.ExcelColumn;

/**
 * @author Alvin
 */
@Data
public abstract class BaseEntity {
    protected Integer ApprovalProjectYear;

    protected Integer memberNum;

    protected Integer SignedOrder;

    @ExcelColumn(value = "科研分数", col = 7)
    protected float score;
}
