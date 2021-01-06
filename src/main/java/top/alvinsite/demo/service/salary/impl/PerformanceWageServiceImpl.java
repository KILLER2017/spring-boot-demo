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

    /**
     * 级差系数
     */
    private static final String INCENTIVE_WAGE_KEY = "a";

    /**
     * 级差系数
     */
    private static final String OVERTIME_WORKED_SUBSIDY_KEY = "b";

    /**
     * 级差系数
     */
    private static final String JOB_SUBSIDY_KEY = "c";

    /**
     * 级差系数
     */
    private static final String LEVEL_FACTOR_KEY = "d";

    /**
     * 年度目标教学工作量
     */
    private static final String TEACHING_WORKLOAD_TARGET_KEY = "e";

    /**
     * 年度目标科研工作量
     */
    private static final String RESEARCH_WORKLOAD_TARGET_KEY = "f";

    /**
     * 实际完成课程教学工作量
     */
    private static final String TEACHING_WORKLOAD_KEY = "g";

    /**
     * 实际完成科研工作量
     */
    private static final String RESEARCH_WORKLOAD_KEY = "h";

    /**
     * 个人实际民主测评值
     */
    private static final String MEASUREMENT_KEY = "i";


    /**
     * 实际完成教研教改工作量
     */
    private static final String TEACHING_RESEARCH_WORKLOAD_KEY = "j";

    /**
     * 实际完成总实验教学工作量
     */
    private static final String EXPERIMENTAL_TEACHING_WORKLOAD_KEY = "k";

    /**
     * 年度目标实验教学工作量
     */
    private static final String EXPERIMENTAL_TEACHING_WORKLOAD_TARGET_KEY = "l";

    /**
     * 个人岗位绩效考核绩点
     */
    private static String GPA_KEY = "m";

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
    private PerformanceWageFormulaService salaryFormulaService;

    @Override
    public List<PerformanceWage> findAll(PerformanceQuery query) {
        List<PerformanceWage> list = baseMapper.findAll(query);
        list.stream()
                .map(this::calcGpa)
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

    private PerformanceWage calcGpa(PerformanceWage performanceWage) {
        String gpaFormula = getGpaFormula(performanceWage);

        if (gpaFormula == null) {
            log.error("业绩绩点计算公式为空，跳过计算，参数：[{}]", performanceWage);
            return performanceWage;
        }

        JexlContext jexlContext = new MapContext();
        JexlEngine jexlEngine = new JexlBuilder().create();
        try {
            JexlExpression expression = jexlEngine.createExpression(gpaFormula);
            handleFormulaContext(gpaFormula, jexlContext, performanceWage);
            performanceWage.setPerformanceGpa((Double) expression.evaluate(jexlContext));
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (JexlException e) {
            String errorMessage = String.format("%s年，%s系列岗位，%s岗位类型的绩点计算规则（%s）不合法", performanceWage.getYear(), performanceWage.getPost(), performanceWage.getPostType(), gpaFormula);
            throw new MyJexlExpression(errorMessage, e);
        }
        return performanceWage;
    }

    private PerformanceWage calcPerformanceWage(PerformanceWage performanceWage) {
        if (performanceWage.getLevel() == null ||
                performanceWage.getPostType() == null
        ) {
            log.error("职务级别或类型岗位为空，无法匹配薪酬计算规则，跳过绩效工资计算：{}", performanceWage);
            return performanceWage;
        }

        String salaryFormula = getPerformanceWageCalcFormula(performanceWage);

        if (salaryFormula == null) {
            log.error("业绩薪酬计算公式为空，跳过计算，参数[{}]", performanceWage);
            return performanceWage;
        }

        JexlContext jexlContext = new MapContext();
        JexlEngine jexlEngine = new JexlBuilder().create();
        try {
            JexlExpression salaryExpression = jexlEngine.createExpression(salaryFormula);

            if (salaryFormula.contains(GPA_KEY)) {
                String gpaFormula = getGpaFormula(performanceWage);
                if (gpaFormula == null) {
                    log.error("业绩绩点计算公式[{}]，跳过计算", performanceWage);
                    return performanceWage;
                }
                JexlExpression gpaExpression = jexlEngine.createExpression(gpaFormula);
                handleFormulaContext(gpaFormula, jexlContext, performanceWage);
                jexlContext.set("m", gpaExpression.evaluate(jexlContext));
            }

            handleFormulaContext(salaryFormula, jexlContext, performanceWage);
            performanceWage.setPerformanceWage((Double) salaryExpression.evaluate(jexlContext));
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (JexlException e) {
            String errorMessage = String.format("%s年，%s系列岗位，%s岗位类型的规则（%s）不合法", performanceWage.getYear(), performanceWage.getPost(), performanceWage.getPostType(), salaryFormula);
            throw new MyJexlExpression(errorMessage);
        }
        return performanceWage;
    }

    private void handleFormulaContext(String expression, JexlContext jexlContext, PerformanceWage performanceWage) {

        // 处理激励绩效工资
        handleFormulaIncentiveWageContext(expression, jexlContext, performanceWage);
        // 处理超课时津贴
        handleFormulaOvertimeWorkedSubsidyContext(expression, jexlContext, performanceWage);
        // 处理岗位津贴
        handleFormulaJobSubsidyContext(expression, jexlContext, performanceWage);

        // 处理级差系数
        handleFormulaLevelFactorContext(expression, jexlContext, performanceWage);
        // 处理目标工作量
        handleFormulaWorkloadTargetContext(expression, jexlContext, performanceWage);

        // 实际教学工作量
        if (expression.contains(TEACHING_WORKLOAD_KEY)) {

            jexlContext.set(TEACHING_WORKLOAD_KEY, performanceWage.getTeachingWorkload());
        }

        // 实际科研工作量
        if (expression.contains(RESEARCH_WORKLOAD_KEY)) {
            jexlContext.set(RESEARCH_WORKLOAD_KEY, performanceWage.getResearchWorkload());
        }

        // 个人实际民主测评值
        if (expression.contains(MEASUREMENT_KEY)) {
            jexlContext.set(MEASUREMENT_KEY, performanceWage.getMeasurement());
        }

        // 实际完成教研教改工作量
        if (expression.contains(TEACHING_RESEARCH_WORKLOAD_KEY)) {
            jexlContext.set(TEACHING_RESEARCH_WORKLOAD_KEY, performanceWage.getTeachingResearchWorkload());
        }

        // 实际完成总实验教学工作量
        if (expression.contains(EXPERIMENTAL_TEACHING_WORKLOAD_KEY)) {
            jexlContext.set(EXPERIMENTAL_TEACHING_WORKLOAD_KEY, performanceWage.getExperimentalTeachingWorkload());
        }
    }

    private void handleFormulaJobSubsidyContext(String expression, JexlContext jexlContext, PerformanceWage performanceWage) {
        if (expression.contains(JOB_SUBSIDY_KEY)) {
            PerformanceQuery query = new PerformanceQuery();
            query.setYear(performanceWage.getYear());
            query.setAccount(performanceWage.getAccount());

            Double jobSubsidy = jobSubsidyService.getUserJobSubsidy(query);
            // 岗位津贴
            jexlContext.set(JOB_SUBSIDY_KEY, jobSubsidy != null ? jobSubsidy : 0.0);
        }
    }

    private void handleFormulaOvertimeWorkedSubsidyContext(String expression, JexlContext jexlContext, PerformanceWage performanceWage) {
        if (expression.contains(OVERTIME_WORKED_SUBSIDY_KEY)) {
            PerformanceQuery query = new PerformanceQuery();
            query.setYear(performanceWage.getYear());
            query.setAccount(performanceWage.getAccount());

            Double overtimeWorkedSubsidy = overtimeWorkedSubsidyService.getUserOvertimeWorkedSubsidy(query);
            // 超课时津贴
            jexlContext.set(OVERTIME_WORKED_SUBSIDY_KEY, overtimeWorkedSubsidy != null ? overtimeWorkedSubsidy : 0.0);
        }
    }

    private void handleFormulaIncentiveWageContext(String expression, JexlContext jexlContext, PerformanceWage performanceWage) {
        if (expression.contains(INCENTIVE_WAGE_KEY)) {
            PerformanceQuery query = new PerformanceQuery();
            query.setYear(performanceWage.getYear());
            query.setAccount(performanceWage.getAccount());

            Double incentiveWage = incentiveWageService.getUserIncentiveWage(query);
            // 激励绩效工资
            jexlContext.set(INCENTIVE_WAGE_KEY, incentiveWage != null ? incentiveWage : 0.0);
        }
    }

    private void handleFormulaLevelFactorContext(String expression, JexlContext jexlContext, PerformanceWage performanceWage) {
        if (expression.contains(LEVEL_FACTOR_KEY)) {
            LevelFactorParam levelFactorParam = transformFrom(performanceWage, LevelFactorParam.class);
            LevelFactor levelFactor = levelFactorDao.findOneByTypeAndLevel(levelFactorParam);
            if (levelFactor == null) {
                throw new IllegalArgumentException("对应级差系数不能为空：" + levelFactorParam);
            }
            // 级差系数
            jexlContext.set(LEVEL_FACTOR_KEY, levelFactor.getFactor());
        }
    }

    private void handleFormulaWorkloadTargetContext(String expression, JexlContext jexlContext, PerformanceWage performanceWage) {
        if (expression.contains(TEACHING_WORKLOAD_TARGET_KEY)
                || expression.contains(RESEARCH_WORKLOAD_TARGET_KEY)
                || expression.contains(EXPERIMENTAL_TEACHING_WORKLOAD_TARGET_KEY)
        ) {
            WorkloadTargetParam workloadTargetParam = transformFrom(performanceWage, WorkloadTargetParam.class);
            workloadTargetParam.setType(performanceWage.getPostType());
            WorkloadTarget workloadTarget = workloadTargetDao.findOneByLevelAndPostType(workloadTargetParam);

            if (workloadTarget == null) {
                throw new IllegalArgumentException("对应目标工作量不能为空：" + workloadTargetParam);
            }

            // 年度目标教学工作量
            jexlContext.set("e", workloadTarget.getTeachingWorkloadTarget());
            // 年度目标科研工作量
            jexlContext.set("f", workloadTarget.getResearchWorkloadTarget());
            // 年度目标实验教学工作量
            jexlContext.set("l", workloadTarget.getExperimentalTeachingWorkloadTarget());
        }
    }

    @Override
    public Double getUserPerformanceWage(PerformanceQuery query) {
        PerformanceWage performanceWage = baseMapper.findOneByAccountAndYear(query);
        if (performanceWage == null) {
            return 0.0;
        }

        calcPerformanceWage(performanceWage);
        return performanceWage.getPerformanceWage();
    }
}
