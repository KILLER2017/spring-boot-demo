package top.alvinsite.demo.dao.performance;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.entity.performance.Paper;
import top.alvinsite.demo.model.params.PerformanceQuery;

import java.util.List;

/**
 * @author Alvin
 */
@Repository
public interface PaperDao extends BaseMapper<Paper> {
    /**
     * 获取论文列表
     * @param performanceQuery 过滤条件
     * @return
     */
    List<Paper> findPaper(PerformanceQuery performanceQuery);
}
