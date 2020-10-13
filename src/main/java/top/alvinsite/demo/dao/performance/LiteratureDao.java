package top.alvinsite.demo.dao.performance;

import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.performance.LiteratureDTO;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.TotalPointsParam;

import java.util.List;

/**
 * 著作成果
 */

@Repository
public interface LiteratureDao {
    public List<LiteratureDTO> findLiterature(PerformanceQuery performanceQuery);
    public Float calcLiteratureTotalPoints(TotalPointsParam totalPointsParam);
}
