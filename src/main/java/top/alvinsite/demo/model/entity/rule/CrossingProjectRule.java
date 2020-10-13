package top.alvinsite.demo.model.entity.rule;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrossingProjectRule {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String department;
    private Integer year;
    private Integer min;
    private Integer max;
    private Float projectScore;

    private Float budgetScoreFactor;
}
