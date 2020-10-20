package top.alvinsite.demo.model.entity.rule;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.dto.rule.BaseRuleDTO;
import top.alvinsite.demo.model.enums.BookType;
import top.alvinsite.demo.model.enums.PublisherLevel;

import javax.validation.constraints.NotNull;

@Data
@Alias("LiteratureRule")
public class LiteratureRule extends BaseRuleEntity  {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String department;
    private Integer year;

    @NotNull(message = "著作类别不能为空")
    private BookType type;

    @NotNull(message = "出版社级别不能为空")
    private PublisherLevel publisherLevel;

    @NotNull(message = "总字数缺少最小值")
    private Integer min;

    @NotNull(message = "总字数缺少最大值")
    private Integer max;

    @NotNull(message = "绩效分数不能为空")
    private Float score;
}
