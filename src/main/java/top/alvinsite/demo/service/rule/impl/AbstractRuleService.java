package top.alvinsite.demo.service.rule.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.alvinsite.demo.model.entity.rule.BaseRuleEntity;
import top.alvinsite.demo.model.params.RuleQuery;
import top.alvinsite.demo.service.rule.IRuleService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alvin
 */
public abstract class AbstractRuleService<M extends BaseMapper<T>, N, T extends BaseRuleEntity> extends ServiceImpl<M, T> implements IRuleService<N, T> {

    @Override
    public void copyRule(String sourceDepartment, int sourceYear, String targetDepartment, int targetYear) {
        // 读取源规则
        RuleQuery query = new RuleQuery();
        query.setDepartment(sourceDepartment);
        query.setYear(sourceYear);
        List<T> rules = findAll(query);

        List<T> targetRules = rules.stream().map(item -> {
            item.setDepartment(targetDepartment);
            item.setYear(targetYear);
            return item;
        }).collect(Collectors.toList());

        this.remove(Wrappers.<T>lambdaQuery()
                .eq(T::getDepartment, sourceDepartment)
                .eq(T::getYear, sourceYear)
        );
        // 保存规则
        this.saveBatch(targetRules);
    }
}
