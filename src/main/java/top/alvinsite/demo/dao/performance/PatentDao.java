package top.alvinsite.demo.dao.performance;

import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.performance.PatentDTO;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.TotalPointsParam;

import java.util.List;

@Repository
public interface PatentDao {
    List<PatentDTO> findPatent(PerformanceQuery performanceQuery);
    public Float calcPatentTotalPoints(TotalPointsParam totalPointsParam);
}
