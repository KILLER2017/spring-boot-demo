package top.alvinsite.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.performance.ResearcherPerformance;
import top.alvinsite.demo.model.entity.Researcher;
import top.alvinsite.demo.model.param.PerformanceQuery;

import java.util.List;

/**
 * @author Alvin
 */
@Repository
public interface ResearcherDao extends BaseMapper<ResearcherPerformance> {
    public List<ResearcherPerformance> findAll(PerformanceQuery performanceQuery);
    public Researcher findOne(String account);
}
