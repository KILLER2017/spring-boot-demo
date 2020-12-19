package top.alvinsite.demo.dao.salary;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.entity.salary.JobSubsidy;
import top.alvinsite.demo.model.params.PerformanceQuery;

import java.util.List;

/**
 * @author Alvin
 */
@Repository
public interface JobSubsidyDao extends BaseMapper<JobSubsidy> {
    List<JobSubsidy> findAll(PerformanceQuery query);
}
