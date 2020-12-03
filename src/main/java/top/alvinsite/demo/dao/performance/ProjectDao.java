package top.alvinsite.demo.dao.performance;

import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.entity.performance.CrossingProject;
import top.alvinsite.demo.model.entity.performance.LongitudinalProject;
import top.alvinsite.demo.model.params.PerformanceQuery;

import java.util.List;

/**
 * @author Alvin
 */
@Repository
public interface ProjectDao {

    /**
     * 获取纵向项目列表
     * @param performanceQuery 过滤条件
     * @return
     */
    List<LongitudinalProject> findLongitudinalProject(PerformanceQuery performanceQuery);

    /**
     * 获取横向项目列表
     * @param performanceQuery 过滤条件
     * @return
     */
    List<CrossingProject> findCrossingProject(PerformanceQuery performanceQuery);
    ManagerUserDTO findMembersById(String id);

}
