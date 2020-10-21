package top.alvinsite.demo.dao.performance;

import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.performance.CrossingProjectDTO;
import top.alvinsite.demo.model.dto.performance.LongitudinalProjectDTO;
import top.alvinsite.demo.model.entity.performance.CrossingProject;
import top.alvinsite.demo.model.entity.performance.LongitudinalProject;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.TotalPointsParam;

import java.util.List;

@Repository
public interface ProjectDao {
    public List<LongitudinalProject> findLongitudinalProject(PerformanceQuery performanceQuery);
    public List<CrossingProject> findCrossingProject(PerformanceQuery performanceQuery);

}
