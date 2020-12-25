package top.alvinsite.demo.dao.salary;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.entity.salary.IncentiveWage;
import top.alvinsite.demo.model.params.PerformanceQuery;

import java.util.List;

/**
 * @author Alvin
 */
@Repository
public interface IncentiveWageDao extends BaseMapper<IncentiveWage> {

    /**
     * 获取激励绩效工资列表
     * @param query 查询参数
     * @return 列表数据
     */
    List<IncentiveWage> findAll(PerformanceQuery query);

    /**
     * 获取指定用户的激励绩效工资
     * @param query 查询参数
     * @return 激励绩效工资
     */
    Double getUserIncentiveWage(PerformanceQuery query);
}
