package top.alvinsite.demo.dao.performance;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.entity.performance.CrossingProject;

/**
 * @author Alvin
 */
@Repository
public interface CrossingProjectDao extends BasePerformanceMapper<CrossingProject>, BaseMapper<CrossingProject> {


}
