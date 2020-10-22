package top.alvinsite.demo.model.entity.rule;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.apache.ibatis.type.Alias;
import top.alvinsite.demo.model.enums.SubsidizeFrom;

@Data
@Alias("LiteratureRuleFundingSource")
public class LiteratureRuleFundingSource extends BaseRuleEntity  {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String department;
    private Integer year;
    private SubsidizeFrom type;
    private Float score;
}
