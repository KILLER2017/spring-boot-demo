package top.alvinsite.demo.dao.performance;

import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.performance.AwardedDTO;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.TotalPointsParam;

import java.util.List;

@Repository
public interface AwardedDAO {
    List<AwardedDTO> findAwarded(PerformanceQuery performanceQuery);
    public Float calcAwardedTotalPoints(TotalPointsParam totalPointsParam);
}
