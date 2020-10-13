package top.alvinsite.demo.dao.salary;

import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.salary.SalarySummaryDTO;
import top.alvinsite.demo.model.entity.salary.SalarySummary;
import top.alvinsite.demo.model.params.PerformanceQuery;

import java.util.List;

@Repository
public interface SalarySummaryDao {
    List<SalarySummary> findAll(PerformanceQuery performanceQuery);
    SalarySummary findOneByAccountAndYear(PerformanceQuery performanceQuery);
    void saveBatch(List<SalarySummary> salarySummaries);
}
