package top.alvinsite.demo.model.entity.rule;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LongitudinalProjectRule {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String department;
    private Integer year;
    private String type;
    private String level;

    private Float projectScore;

    private Float budgetScoreFactor;
}
