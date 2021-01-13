package top.alvinsite.demo.dao.rule;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.entity.rule.PaperRule;
import top.alvinsite.demo.model.param.RuleQuery;

import java.util.List;

/**
 *
 * @author Alvin
 */
@Repository
public interface PaperRuleDao extends BaseMapper<PaperRule> {
    List<PaperRule> findAll(RuleQuery ruleQuery);


    @Select("    select\n" +
            "    paper_rule.id,\n" +
            "    paper_rule.department,\n" +
            "    paper_rule.year,\n" +
            "    dm_paper_type.ID AS type,\n" +
            "    paper_rule.score\n" +
            "    from paper_rule\n" +
            "    left join dm_paper_type on paper_rule.type = dm_paper_type.id\n" +
            "    ${ew.customSqlSegment}")
    List<PaperRule> findAllByWrapper(@Param(Constants.WRAPPER) Wrapper wrapper);

}
