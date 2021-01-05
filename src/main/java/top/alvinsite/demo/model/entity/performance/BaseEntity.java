package top.alvinsite.demo.model.entity.performance;

import lombok.Data;
import top.alvinsite.demo.model.entity.Department;
import top.alvinsite.demo.model.support.Excel;

/**
 * @author Alvin
 */
@Data
public abstract class BaseEntity {

    private String id;

    @Excel(name = "姓名", col = 1)
    private String nickname;

    @Excel(name = "工号", col = 2)
    private String account;

    @Excel(name = "所属单位", col = 3)
    private Department department;

    protected Integer ApprovalProjectYear;

    protected Integer memberNum;

    protected Integer SignedOrder;

    @Excel(name = "科研分数", col = 20)
    protected double score;
}
