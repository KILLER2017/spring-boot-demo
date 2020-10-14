package top.alvinsite.demo.service.performance;

import org.springframework.beans.factory.annotation.Autowired;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.service.ScoreDistributionService;
import top.alvinsite.demo.service.rule.ScoreDistributionConfigService;

import java.util.List;

public interface BasePerformanceService<T>  {

    List<T> findAll(PerformanceQuery performanceQuery) throws Exception;

    default T getProjectMemberNum(T project) {
        return project;
    };

    default T calcTotalPoints(T project) {
        return project;
    };
}
