package top.alvinsite.demo.dao.performance;

import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.performance.PaperDTO;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.TotalPointsParam;

import java.util.List;

@Repository
public interface PaperDao {
    public List<PaperDTO> findPaper(PerformanceQuery performanceQuery);
    public Float calcPaperTotalPoints(TotalPointsParam totalPointsParam);
}
