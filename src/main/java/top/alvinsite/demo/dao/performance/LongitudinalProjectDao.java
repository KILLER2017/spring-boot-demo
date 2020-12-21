package top.alvinsite.demo.dao.performance;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.entity.performance.LongitudinalProject;

/**
 * @author Alvin
 */
@Repository
public interface LongitudinalProjectDao extends BasePerformanceMapper<LongitudinalProject>, BaseMapper<LongitudinalProject> {

}
