package top.alvinsite.demo.service.salary;

import com.baomidou.mybatisplus.extension.service.IService;
import top.alvinsite.demo.model.entity.salary.Rule;

import java.util.List;

/**
 * @author Administrator
 */
public interface RuleService extends IService<Rule> {
    List<Rule> list(Integer year);
    void saveBatch(String deleteIds, List<Rule> rules);
}
