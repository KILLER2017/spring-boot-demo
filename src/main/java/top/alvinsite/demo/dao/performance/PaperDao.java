package top.alvinsite.demo.dao.performance;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.performance.PaperDTO;
import top.alvinsite.demo.model.entity.performance.Paper;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.TotalPointsParam;

import java.util.List;

@Repository
public interface PaperDao extends BaseMapper<Paper> {
    public List<Paper> findPaper(PerformanceQuery performanceQuery);
    public Float calcPaperTotalPoints(TotalPointsParam totalPointsParam);
}
