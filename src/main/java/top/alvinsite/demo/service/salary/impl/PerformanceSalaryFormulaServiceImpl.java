package top.alvinsite.demo.service.salary.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.salary.PerformanceSalaryFormulaDao;
import top.alvinsite.demo.model.entity.salary.PerformanceWageFormula;
import top.alvinsite.demo.service.salary.PerformanceSalaryFormulaService;

/**
 * @author Alvin
 */
@Service
public class PerformanceSalaryFormulaServiceImpl extends ServiceImpl<PerformanceSalaryFormulaDao, PerformanceWageFormula> implements PerformanceSalaryFormulaService {
}
