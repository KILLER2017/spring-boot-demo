package top.alvinsite.demo.dao.salary;

import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.entity.salary.WorkloadTarget;
import top.alvinsite.demo.model.params.SalaryQuery;
import top.alvinsite.demo.model.params.WorkloadTargetParam;

import java.util.List;

@Repository
public interface WorkloadTargetDao {
    List<WorkloadTarget> findAll(SalaryQuery salaryQuery);
    WorkloadTarget findOne(Integer id);
    WorkloadTarget findOneByLevelAndPostType(WorkloadTargetParam workloadTargetParam);
    void save(WorkloadTarget workLoadTarget);
}
