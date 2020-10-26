package top.alvinsite.demo.dao.performance;

import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.entity.performance.CrossingProject;
import top.alvinsite.demo.model.entity.performance.LongitudinalProject;
import top.alvinsite.demo.model.params.PerformanceQuery;

import java.util.List;

/**
 * @author Alvin
 */
@Repository
public interface ProjectDao {
    List<LongitudinalProject> findLongitudinalProject(PerformanceQuery performanceQuery);
    List<CrossingProject> findCrossingProject(PerformanceQuery performanceQuery);

}
