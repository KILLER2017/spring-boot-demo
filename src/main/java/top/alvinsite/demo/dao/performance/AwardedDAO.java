package top.alvinsite.demo.dao.performance;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.performance.AwardedDTO;
import top.alvinsite.demo.model.entity.performance.Awarded;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.TotalPointsParam;

import java.util.List;

@Repository
public interface AwardedDAO extends BaseMapper<Awarded> {
    List<Awarded> findAwarded(PerformanceQuery performanceQuery);
    public Float calcAwardedTotalPoints(TotalPointsParam totalPointsParam);
}
