package top.alvinsite.demo.model.entity.salary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@Alias("Rule")
@AllArgsConstructor
@NoArgsConstructor
public class Rule {
    private Integer id;
    private Integer year;
    private String type;
    private String postType;
    private String alias;
    private String rule;
}
