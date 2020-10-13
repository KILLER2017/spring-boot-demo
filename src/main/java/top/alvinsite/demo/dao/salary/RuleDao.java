package top.alvinsite.demo.dao.salary;

import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.entity.salary.Rule;
import top.alvinsite.demo.model.params.SalaryRuleParam;

import java.util.List;

@Repository
public interface RuleDao {
    List<Rule> findAll(Integer year);
    Rule findOne(SalaryRuleParam salaryRuleParam);
    void save(Rule rule);
    void saveBatch(List<Rule> rules);
    void delete(Integer id);
    void deleteByIds(String[] ids);
}
