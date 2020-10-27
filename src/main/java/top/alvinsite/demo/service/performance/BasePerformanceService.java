package top.alvinsite.demo.service.performance;

import top.alvinsite.demo.model.dto.performance.ResearcherPerformance;
import top.alvinsite.demo.model.entity.performance.BaseEntity;
import top.alvinsite.demo.model.params.PerformanceQuery;

import java.util.List;

public interface BasePerformanceService<T extends BaseEntity>  {

    List<T> findAll(PerformanceQuery performanceQuery);

    default T getProjectMemberNum(T project) {
        return project;
    };

    default T calcProjectPoints(T project) {
        return project;
    };

    void setTotalPoints(ResearcherPerformance researcherPerformance, float totalPoints);

    default ResearcherPerformance calcTotalPoints(ResearcherPerformance researcherPerformance) {
        PerformanceQuery query = new PerformanceQuery();
        query.setAccount(researcherPerformance.getAccount());
        query.setYear(researcherPerformance.getYear());

        List<T> list = findAll(query);
        float totalPoints = 0;
        for (T awarded : list) {
            this.calcProjectPoints(awarded);
            totalPoints += awarded.getScore();
        }

        setTotalPoints(researcherPerformance, totalPoints);
        return researcherPerformance;
    };
}
