package top.alvinsite.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.SalaryQuery;
import top.alvinsite.framework.springsecurity.entity.User;

import java.util.Arrays;

/**
 * @author Alvin
 */
@Component
@Aspect
public class ParamFilterAspect {

    private final static String SUPER_USER_GROUP = "admin";
    private final static String MANAGER_GROUP = "manager";


    @Pointcut("execution(*  top.alvinsite.demo.controller.salary.*.*(..))")
    public void salaryController() {
    }

    @Around("salaryController()")
    public Object controller(ProceedingJoinPoint joinPoint) throws Throwable {
        if (joinPoint.getArgs().length < 1) {
            return joinPoint.proceed();
        }
        Object object =  joinPoint.getArgs()[0];
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (object instanceof SalaryQuery) {
            SalaryQuery salaryQuery = (SalaryQuery) object;

            if (MANAGER_GROUP.equals(user.getUserGroup()) && !Arrays.asList(user.getManageUnits()).contains(salaryQuery.getDepartment())) {
                throw new IllegalArgumentException("部门参数错误，只能向您管理的部门导入数据");
            }
        }

        if (object instanceof PerformanceQuery) {
            PerformanceQuery query = (PerformanceQuery) object;

            if (!SUPER_USER_GROUP.equals(user.getUserGroup()) && user.getManageUnits() != null) {
                query.setDepartmentScope(user.getManageUnits());
                if (query.getDepartmentId() == null) {
                    query.setDepartmentId(user.getManageUnitId());
                }
            }
        }
        return joinPoint.proceed();
    }
}
