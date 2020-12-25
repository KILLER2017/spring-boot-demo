package top.alvinsite.demo.dao.salary;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.entity.salary.WorkloadTarget;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.WorkloadTargetParam;

import java.util.List;

/**
 * @author Alvin
 */
@Repository
public interface WorkloadTargetDao extends BaseMapper<WorkloadTarget> {

    /**
     * 获取目标工作量列表
     * @param query 查询参数
     * @return 列表数据
     */
    List<WorkloadTarget> findAll(PerformanceQuery query);

    /**
     * 根据ID获取目标工作量
     * @param id ID
     * @return 目标工作量
     */
    WorkloadTarget findOne(Integer id);

    /**
     * 获取指定一个目标工作量信息
     * @param workloadTargetParam 查询参数
     * @return 目标工作量
     */
    WorkloadTarget findOneByLevelAndPostType(WorkloadTargetParam workloadTargetParam);

    /**
     * 保存目标工作量信息
     * @param workLoadTarget 目标工作量
     */
    void save(WorkloadTarget workLoadTarget);

}
