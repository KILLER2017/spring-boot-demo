package top.alvinsite.demo.dao.performance;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.performance.CopyrightDTO;
import top.alvinsite.demo.model.entity.performance.Copyright;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.TotalPointsParam;

import java.util.List;

@Repository
public interface CopyrightDao extends BaseMapper<Copyright> {
    List<Copyright> findCopyright(PerformanceQuery performanceQuery);
}
