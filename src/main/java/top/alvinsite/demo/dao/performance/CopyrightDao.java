package top.alvinsite.demo.dao.performance;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.entity.performance.Copyright;
import top.alvinsite.demo.model.params.PerformanceQuery;

import java.util.List;

/**
 * @author Alvin
 */
@Repository
public interface CopyrightDao extends BaseMapper<Copyright> {

    /**
     * 获取著作权科研绩效列表
     * @param performanceQuery 过滤条件
     * @return 科研绩效列表
     */
    List<Copyright> findCopyright(PerformanceQuery performanceQuery);
}
