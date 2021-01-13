package top.alvinsite.demo.service.salary.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.salary.SalarySummaryDao;
import top.alvinsite.demo.model.entity.salary.SalarySummary;
import top.alvinsite.demo.model.param.PerformanceQuery;
import top.alvinsite.demo.service.salary.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alvin
 */
@Slf4j
@Service
public class SalarySummaryServiceImpl extends ServiceImpl<SalarySummaryDao, SalarySummary> implements SalarySummaryService {

    @Autowired
    private IncentiveWageService incentiveWageService;

    @Autowired
    private JobSubsidyService jobSubsidyService;

    @Autowired
    private OvertimeWorkedSubsidyService overtimeWorkedSubsidyService;

    @Autowired
    private PerformanceWageService performanceWageService;

    @Override
    public List<SalarySummary> findAll(PerformanceQuery query) {
        List<SalarySummary> list = baseMapper.findAll(query);
        list.stream()
                .map(this::setPerformanceWage)
                .map(this::setIncentiveWage)
                .map(this::setOvertimeWorkedSubsidy)
                .map(this::setJobSubsidy)
                .map(this::setTotalSalary)
                .collect(Collectors.toList());
        return list;
    }

    /**
     * 检查薪酬是否为空，若为空则返回0
     * @param salary 待检查的薪酬
     * @return 薪酬
     */
    private Double salaryChecker(Double salary) {
        if (salary == null) {
            return 0.0;
        }
        return salary;
    }

    private SalarySummary setPerformanceWage(SalarySummary detail) {
        // todo 计算业绩绩效工资
        PerformanceQuery query = new PerformanceQuery();
        query.setAccount(detail.getAccount());
        query.setYear(detail.getYear());

        Double salary = salaryChecker(performanceWageService.getUserPerformanceWage(query));
        detail.setPerformanceWage(salary);
        return detail;
    }

    private SalarySummary setIncentiveWage(SalarySummary detail) {
        PerformanceQuery query = new PerformanceQuery();
        query.setAccount(detail.getAccount());
        query.setYear(detail.getYear());
        Double salary = salaryChecker(incentiveWageService.getUserIncentiveWage(query));
        detail.setIncentiveWage(salary);
        return detail;
    }

    private SalarySummary setOvertimeWorkedSubsidy(SalarySummary detail) {
        PerformanceQuery query = new PerformanceQuery();
        query.setAccount(detail.getAccount());
        query.setYear(detail.getYear());
        Double salary = salaryChecker(overtimeWorkedSubsidyService.getUserOvertimeWorkedSubsidy(query));
        detail.setOvertimeSubsidy(salary);
        return detail;
    }

    private SalarySummary setJobSubsidy(SalarySummary detail) {
        PerformanceQuery query = new PerformanceQuery();
        query.setAccount(detail.getAccount());
        query.setYear(detail.getYear());

        Double salary = salaryChecker(jobSubsidyService.getUserJobSubsidy(query));
        detail.setJobSubsidy(salary);
        return detail;
    }

    private SalarySummary setTotalSalary(SalarySummary detail) {
        Double totalSalary = detail.getPerformanceWage()
                + detail.getIncentiveWage()
                + detail.getOvertimeSubsidy()
                + detail.getJobSubsidy()
                + detail.getSpecialSubsidy();
        detail.setTotalSalary(totalSalary);
        return detail;
    }
}
