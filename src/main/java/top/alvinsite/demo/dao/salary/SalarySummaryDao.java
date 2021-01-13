package top.alvinsite.demo.dao.salary;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.entity.salary.SalarySummary;
import top.alvinsite.demo.model.param.PerformanceQuery;

import java.util.List;

/**
 * @author Alvin
 */
@Repository
public interface SalarySummaryDao extends BaseMapper<SalarySummary> {

    /**
     * 获取工资详情列表
     * @param query 查询参数
     * @return 列表数据
     */
    List<SalarySummary> findAll(PerformanceQuery query);
}
