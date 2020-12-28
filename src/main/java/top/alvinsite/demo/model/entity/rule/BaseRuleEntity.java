package top.alvinsite.demo.model.entity.rule;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author Alvin
 */
@Data
public class BaseRuleEntity {

    @TableId(type = IdType.AUTO)
    protected Integer id;

    protected String department;

    protected Integer year;
}
