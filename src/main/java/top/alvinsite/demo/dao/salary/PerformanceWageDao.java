package top.alvinsite.demo.dao.salary;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.entity.salary.PerformanceWage;
import top.alvinsite.demo.model.param.PerformanceQuery;

import java.util.List;

/**
 * @author Alvin
 */
@Repository
public interface PerformanceWageDao extends BaseMapper<PerformanceWage> {

    /**
     * 获取业绩绩效工资列表
     * @param performanceQuery 查询参数
     * @return 列表数据
     */
    List<PerformanceWage> findAll(PerformanceQuery performanceQuery);

    /**
     * 获取指定一个用户的业绩绩效工资
     * @param performanceQuery 查询参数
     * @return 业绩绩效工资
     */
    PerformanceWage findOneByAccountAndYear(PerformanceQuery performanceQuery);
}
