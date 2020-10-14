package top.alvinsite.demo.dao.performance;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.performance.PatentDTO;
import top.alvinsite.demo.model.entity.performance.Patent;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.TotalPointsParam;

import java.util.List;

@Repository
public interface PatentDao extends BaseMapper<Patent> {
    List<Patent> findPatent(PerformanceQuery performanceQuery);
    public Float calcPatentTotalPoints(TotalPointsParam totalPointsParam);
}
