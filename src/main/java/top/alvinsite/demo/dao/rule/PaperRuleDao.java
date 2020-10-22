package top.alvinsite.demo.dao.rule;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.rule.PaperRuleDTO;
import top.alvinsite.demo.model.entity.performance.Paper;
import top.alvinsite.demo.model.entity.rule.PaperRule;
import top.alvinsite.demo.model.params.RuleQuery;

import java.util.List;

@Repository
public interface PaperRuleDao extends BaseMapper<PaperRule> {
    List<PaperRuleDTO> findAll(RuleQuery ruleQuery);


    @Select("    select\n" +
            "    paper_rule.id,\n" +
            "    paper_rule.department,\n" +
            "    paper_rule.year,\n" +
            "    DM_PAPER_TYPE.NAME AS type,\n" +
            "    paper_rule.score\n" +
            "    from paper_rule\n" +
            "    left join DM_PAPER_TYPE on paper_rule.type = DM_PAPER_TYPE.id\n" +
            "    ${ew.customSqlSegment}")
    List<PaperRule> findAllByWrapper(@Param(Constants.WRAPPER) Wrapper wrapper);
    void saveBatch(List<PaperRule> paperRules);

}
