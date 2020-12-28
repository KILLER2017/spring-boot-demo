package top.alvinsite.demo.model.entity.rule;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.enums.BookType;
import top.alvinsite.demo.model.enums.PublisherLevel;

import javax.validation.constraints.NotNull;

/**
 * @author Alvin
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Alias("LiteratureRule")
public class LiteratureRule extends BaseRuleEntity  {

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
