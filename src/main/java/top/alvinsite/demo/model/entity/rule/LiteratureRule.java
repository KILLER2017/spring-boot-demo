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

    @NotNull(message = "字数因子不能为空")
    private Double factor;
}
