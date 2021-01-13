package top.alvinsite.demo.dao.salary;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.entity.salary.OvertimeWorkedSubsidy;
import top.alvinsite.demo.model.param.PerformanceQuery;

import java.util.List;

/**
 * @author Alvin
 */
@Repository
public interface OvertimeWorkedSubsidyDao extends BaseMapper<OvertimeWorkedSubsidy> {

    /**
     * 获取超课时津贴列表
     * @param query 查询参数
     * @return 列表数据
     */
    List<OvertimeWorkedSubsidy> findAll(PerformanceQuery query);

    /**
     * 获取指定一个用户的超课时津贴
     * @param query 查询参数
     * @return 超课时津贴
     */
    Double getUserOvertimeWorkedSubsidy(PerformanceQuery query);
}
