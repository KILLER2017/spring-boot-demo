package top.alvinsite.demo.service.salary;

import com.baomidou.mybatisplus.extension.service.IService;
import top.alvinsite.demo.model.entity.salary.GpaFormula;
import top.alvinsite.demo.model.param.SalaryRuleQuery;

import java.util.List;

/**
 * @author Administrator
 */
public interface GpaFormulaService extends IService<GpaFormula> {

    /**
     * 获取绩点计算公式列表
     * @param salaryRuleQuery 查询参数
     * @return 计算公式列表
     */
    List<GpaFormula> findAll(SalaryRuleQuery salaryRuleQuery);

    /**
     * 批量保存绩点计算公式
     * @param deleteIds 需要删除的计算公式ID
     * @param gpaFormulas 待保存的计算公式列表
     */
    void saveBatch(String deleteIds, List<GpaFormula> gpaFormulas);
}
