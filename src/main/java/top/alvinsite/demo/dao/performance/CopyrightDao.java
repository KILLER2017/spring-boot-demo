package top.alvinsite.demo.dao.performance;

import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.performance.CopyrightDTO;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.TotalPointsParam;

import java.util.List;

@Repository
public interface CopyrightDao {
    List<CopyrightDTO> findCopyright(PerformanceQuery performanceQuery);
    public Float calcCopyrightTotalPoints(TotalPointsParam totalPointsParam);
}
