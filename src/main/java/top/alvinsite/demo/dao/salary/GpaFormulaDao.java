package top.alvinsite.demo.dao.salary;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.entity.salary.GpaFormula;
import top.alvinsite.demo.model.param.SalaryRuleParam;
import top.alvinsite.demo.model.param.SalaryRuleQuery;

import java.util.List;

/**
 * @author Alvin
 */
@Repository
public interface GpaFormulaDao extends BaseMapper<GpaFormula> {

    /**
     * 获取绩点计算公式列表
     * @param salaryRuleQuery 查询参数
     * @return 计算公式列表
     */
    List<GpaFormula> findAll(SalaryRuleQuery salaryRuleQuery);

    /**
     * 获取指定一条绩点计算公式
     * @param salaryRuleParam 查询参数
     * @return 绩点计算公式
     */
    GpaFormula findOne(SalaryRuleParam salaryRuleParam);

    /**
     * 保存绩点计算公式
     * @param gpaFormula 待保存的计算公式
     */
    void save(GpaFormula gpaFormula);

    /**
     * 批量保存计算公式
     * @param gpaFormulas 计算公式列表
     */
    void saveBatch(List<GpaFormula> gpaFormulas);
}
