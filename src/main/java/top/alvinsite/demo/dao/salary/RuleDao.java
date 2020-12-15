package top.alvinsite.demo.dao.salary;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.entity.salary.Rule;
import top.alvinsite.demo.model.params.SalaryRuleParam;
import top.alvinsite.demo.model.params.SalaryRuleQuery;

import java.util.List;

@Repository
public interface RuleDao extends BaseMapper<Rule> {
    List<Rule> findAll(SalaryRuleQuery salaryRuleQuery);
    Rule findOne(SalaryRuleParam salaryRuleParam);
    void save(Rule rule);
    void saveBatch(List<Rule> rules);
}
