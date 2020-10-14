package top.alvinsite.demo.dao.performance;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.performance.LiteratureDTO;
import top.alvinsite.demo.model.entity.performance.Literature;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.TotalPointsParam;

import java.util.List;

/**
 * 著作成果
 */

@Repository
public interface LiteratureDao extends BaseMapper<Literature> {
    public List<Literature> findLiterature(PerformanceQuery performanceQuery);
    public Float calcLiteratureTotalPoints(TotalPointsParam totalPointsParam);
}
