package top.alvinsite.demo.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import top.alvinsite.demo.model.entity.rule.LiteratureRuleFundingSource;
import top.alvinsite.demo.model.entity.rule.LiteratureRuleRevised;
import top.alvinsite.demo.model.entity.rule.LiteratureRuleTopicWithDongguan;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class LiteratureRuleVO extends RuleVO {
    List<LiteratureRuleFundingSource> fundingSourceList;
    List<LiteratureRuleTopicWithDongguan> topicWithDongguanList;
    List<LiteratureRuleRevised> revisedList;
}
