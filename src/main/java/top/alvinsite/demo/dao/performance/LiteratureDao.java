package top.alvinsite.demo.dao.performance;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.entity.performance.Literature;
import top.alvinsite.demo.model.param.PerformanceQuery;

import java.util.List;

/**
 * 著作成果
 * @author Alvin
 */

@Repository
public interface LiteratureDao extends BaseMapper<Literature> {

    /**
     * 获取专著列表
     * @param performanceQuery 过滤条件
     * @return
     */
    List<Literature> findLiterature(PerformanceQuery performanceQuery);
}
