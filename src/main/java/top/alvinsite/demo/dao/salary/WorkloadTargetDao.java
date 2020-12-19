package top.alvinsite.demo.dao.salary;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.entity.salary.WorkloadTarget;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.WorkloadTargetParam;

import java.util.List;

@Repository
public interface WorkloadTargetDao extends BaseMapper<WorkloadTarget> {
    List<WorkloadTarget> findAll(PerformanceQuery query);
    WorkloadTarget findOne(Integer id);
    WorkloadTarget findOneByLevelAndPostType(WorkloadTargetParam workloadTargetParam);
    void save(WorkloadTarget workLoadTarget);
}
