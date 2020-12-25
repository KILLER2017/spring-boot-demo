package top.alvinsite.demo.service.salary.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.jexl3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.salary.LevelFactorDao;
import top.alvinsite.demo.dao.salary.GpaFormulaDao;
import top.alvinsite.demo.dao.salary.PerformanceWageDao;
import top.alvinsite.demo.dao.salary.WorkloadTargetDao;
import top.alvinsite.demo.model.entity.salary.*;
import top.alvinsite.demo.model.params.LevelFactorParam;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.SalaryRuleParam;
import top.alvinsite.demo.model.params.WorkloadTargetParam;
import top.alvinsite.demo.service.salary.*;
import top.alvinsite.exception.MyJexlExpression;

import java.util.List;
import java.util.stream.Collectors;

import static top.alvinsite.utils.BeanUtils.transformFrom;

/**
 * @author Alvin
 */
@Slf4j
@Service
public class PerformanceWageServiceImpl extends ServiceImpl<PerformanceWageDao, PerformanceWage> implements PerformanceWageService {

    @Autowired
    private GpaFormulaDao gpaFormulaDao;

    @Autowired
    private LevelFactorDao levelFactorDao;

    @Autowired
    private WorkloadTargetDao workloadTargetDao;

    @Autowired
    private IncentiveWageService incentiveWageService;

    @Autowired
    private OvertimeWorkedSubsidyService overtimeWorkedSubsidyService;

    @Autowired
    private JobSubsidyService jobSubsidyService;

    @Autowired
    private PerformanceSalaryFormulaService salaryFormulaService;

    @Override
    public List<PerformanceWage> findAll(PerformanceQuery query) {
        List<PerformanceWage> list = baseMapper.findAll(query);
        list = list.stream()
                .map(this::calcPerformanceWage)
                .collect(Collectors.toList());
        return list;
    }

    /**
     * 获取业绩绩点计算公式
     * @param performanceWage 业绩绩效工资
     * @return 业绩绩效工资计算公式
     */
    private String getPerformanceWageCalcFormula(PerformanceWage performanceWage) {
        PerformanceWageFormula formula = salaryFormulaService.getOne(Wrappers.<PerformanceWageFormula>lambdaQuery()
                        .eq(PerformanceWageFormula::getYear, performanceWage.getYear())
                        .eq(PerformanceWageFormula::getDepartment, performanceWage.getDepartment())
                , false
        );
        return formula != null ? formula.getFormula() : null;
    }

    /**
     * 获取业绩绩效工资计算公式
     * @param performanceWage 业绩绩效工资
     * @return 业绩绩效绩点计算公式
     */
    private String getGpaFormula(PerformanceWage performanceWage) {
        SalaryRuleParam salaryRuleParam = transformFrom(performanceWage, SalaryRuleParam.class);
        assert salaryRuleParam != null;
        GpaFormula gpaFormula = gpaFormulaDao.findOne(salaryRuleParam);
        return gpaFormula != null ? gpaFormula.getRule() : null;
    }

    private PerformanceWage calcPerformanceWage(PerformanceWage performanceWage) {
        if (performanceWage.getLevel() == null ||
                performanceWage.getPostType() == null
        ) {
            log.error("职务级别或类型岗位为空，跳过绩效工资计算：{}", performanceWage);
            return performanceWage;
        }

        String salaryFormula = getPerformanceWageCalcFormula(performanceWage);
        String gpaFormula = getGpaFormula(performanceWage);
        if (salaryFormula == null || gpaFormula == null) {
            log.error("业绩绩点计算公式[{}]或业绩薪酬计算公式[{}]为空，跳过计算", gpaFormula, salaryFormula);
            return performanceWage;
        }

        JexlContext jexlContext = new MapContext();
        JexlEngine jexlEngine = new JexlBuilder().create();
        try {

            JexlExpression gpaExpression = jexlEngine.createExpression(gpaFormula);
            JexlExpression salaryExpression = jexlEngine.createExpression(salaryFormula);
            handleFormulaContext(jexlContext, performanceWage);
            jexlContext.set("m", gpaExpression.evaluate(jexlContext));
            performanceWage.setPerformanceWage((Double) salaryExpression.evaluate(jexlContext));
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (JexlException e) {
            String errorMessage = String.format("%s年，%s系列岗位，%s岗位类型的规则（%s）不合法", performanceWage.getYear(), performanceWage.getPost(), performanceWage.getPostType(), salaryFormula);
            throw new MyJexlExpression(errorMessage);
        }
        return performanceWage;
    }

    private void handleFormulaContext(JexlContext jexlContext, PerformanceWage performanceWage) {
        PerformanceQuery query = new PerformanceQuery();
        query.setYear(performanceWage.getYear());
        query.setAccount(performanceWage.getAccount());

        Double incentiveWage = incentiveWageService.getUserIncentiveWage(query);
        Double overtimeWorkedSubsidy = overtimeWorkedSubsidyService.getUserOvertimeWorkedSubsidy(query);
        Double jobSubsidy = jobSubsidyService.getUserJobSubsidy(query);


        LevelFactorParam levelFactorParam = transformFrom(performanceWage, LevelFactorParam.class);
        LevelFactor levelFactor = levelFactorDao.findOneByTypeAndLevel(levelFactorParam);

        if (levelFactor == null) {
            throw new IllegalArgumentException("对应级差系数不能为空：" + levelFactorParam);
        }

        WorkloadTargetParam workloadTargetParam = transformFrom(performanceWage, WorkloadTargetParam.class);
        WorkloadTarget workloadTarget = workloadTargetDao.findOneByLevelAndPostType(workloadTargetParam);

        if (workloadTarget == null) {
            throw new IllegalArgumentException("对应目标工作量不能为空：" + workloadTargetParam);
        }

        // 激励绩效工资
        jexlContext.set("a", incentiveWage != null ? incentiveWage : 0.0);
        // 超课时津贴
        jexlContext.set("b", overtimeWorkedSubsidy != null ? overtimeWorkedSubsidy : 0.0);
        // 岗位津贴
        jexlContext.set("c", jobSubsidy != null ? jobSubsidy : 0.0);
        // 级差系数
        jexlContext.set("d", levelFactor.getFactor());
        // 实际教学工作量
        jexlContext.set("g", performanceWage.getTeachingWorkload());
        // 实际科研工作量
        jexlContext.set("h", performanceWage.getResearchWorkload());
        // 年度目标教学工作量
        jexlContext.set("e", workloadTarget.getTeachingWorkloadTarget());
        // 年度目标科研工作量
        jexlContext.set("f", workloadTarget.getResearchWorkloadTarget());
        // 个人实际民主测评值
        jexlContext.set("i", performanceWage.getMeasurement());
        // 实际完成教研教改工作量
        jexlContext.set("j", performanceWage.getTeachingResearchWorkload());
        // 实际完成总实验教学工作量
        jexlContext.set("k", performanceWage.getExperimentalTeachingWorkload());
        // 年度目标实验教学工作量
        jexlContext.set("l", workloadTarget.getExperimentalTeachingWorkloadTarget());
    }

    @Override
    public Double getUserPerformanceWage(PerformanceQuery query) {
        PerformanceWage performanceWage = baseMapper.findOneByAccountAndYear(query);
        calcPerformanceWage(performanceWage);
        return performanceWage.getPerformanceWage();
    }
}
