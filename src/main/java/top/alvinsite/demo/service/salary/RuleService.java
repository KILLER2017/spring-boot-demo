package top.alvinsite.demo.service.salary;

import top.alvinsite.demo.model.entity.salary.Rule;

import java.util.List;

public interface RuleService {
    List<Rule> list(Integer year);
    void saveBatch(String deleteIds, List<Rule> rules);
    void delete(String[] ids);
}
