package top.alvinsite.demo.controller.salary;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import top.alvinsite.demo.model.params.SalaryQuery;
import top.alvinsite.framework.springsecurity.entity.User;

import java.util.Arrays;

/**
 * @author Alvin
 */
@Component
@Aspect
public class ParamFilterAspect {

    @Pointcut("execution(*  top.alvinsite.demo.controller.salary.*.*(..))")
    public void salaryController() {
    }

    @Around("salaryController()")
    public Object controller(ProceedingJoinPoint joinPoint) throws Throwable {

        Object object =  joinPoint.getArgs()[0];
        if (object instanceof SalaryQuery) {
            SalaryQuery salaryQuery = (SalaryQuery) object;

            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if ("manager".equals(user.getUserGroup()) && !Arrays.asList(user.getManageUnits()).contains(salaryQuery.getDepartment())) {
                throw new IllegalArgumentException("部门参数错误，只能向您管理的部门导入数据");
            }
        }
        Object returnObj = joinPoint.proceed();
        return returnObj;
    }
}
