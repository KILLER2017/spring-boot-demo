package top.alvinsite.demo.service.performance;

import top.alvinsite.demo.model.params.PerformanceQuery;

import java.util.List;

public interface BasePerformanceService<T>  {

    List<T> findAll(PerformanceQuery performanceQuery) throws Exception;
    T getProjectMemberNum(T project);
    T calcTotalPoints(T project) throws Exception;
}
