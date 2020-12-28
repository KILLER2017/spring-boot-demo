package top.alvinsite.demo.service.rule;

import com.baomidou.mybatisplus.extension.service.IService;
import top.alvinsite.demo.model.params.RuleQuery;

import java.util.List;

/**
 * 绩效规则服务接口
 * @author Alvin
 */
public interface IRuleService<M, T> extends IService<T> {

    /**
     * 获取绩效规则列表
     * @param ruleQuery 过滤条件
     * @return 绩效规则列表
     */
    List<T> findAll(RuleQuery ruleQuery);

    /**
     * 根据绩效项目获取绩效规则
     * @param project 绩效项目
     * @return 绩效规则
     */
    T findRule(M project);

    /**
     * 根据绩效项目获取绩效分数
     * @param project 绩效项目
     * @return 绩效分数
     */
    float getScore(M project);

    /**
     * 复制绩效规则
     * @param sourceDepartment 源单位
     * @param sourceYear 源年度
     * @param targetDepartment 目标单位
     * @param targetYear 目标年度
     */
    void copyRule(String sourceDepartment, int sourceYear, String targetDepartment, int targetYear);
}
