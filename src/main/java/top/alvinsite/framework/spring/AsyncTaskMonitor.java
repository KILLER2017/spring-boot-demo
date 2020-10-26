package top.alvinsite.framework.spring;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by shirukai on 2018/7/31
 * 异步任务监控
 */
@Component
@Aspect
public class AsyncTaskMonitor {
    @Autowired
    AsyncTaskManager manager;
    private static Logger LOG = LoggerFactory.getLogger(AsyncTaskMonitor.class);

    @Around("execution(* top.alvinsite.framework.spring.service.AsyncTaskExecutor.*(..))")
    public void taskHandle(ProceedingJoinPoint pjp) {
        //获取taskId
        String taskId = pjp.getArgs()[1].toString();
        //获取任务信息
        TaskInfo taskInfo = manager.getTaskInfo(taskId);
        LOG.info("AsyncTaskMonitor is monitoring async task:{}", taskId);
        taskInfo.setStatus(TaskStatusEnum.RUNNING);
        manager.setTaskInfo(taskInfo);
        TaskStatusEnum status = null;
        Object result = null;
        try {
            result = pjp.proceed();
            status = TaskStatusEnum.SUCCESS;
        } catch (Throwable throwable) {
            status = TaskStatusEnum.FAILED;
            taskInfo.setMessage(throwable.getMessage());
            LOG.error("AsyncTaskMonitor:async task {} is failed.Error info:{}", taskId, throwable.getMessage());
        }
        taskInfo.setResult(result);
        taskInfo.setEndTime(new Date());
        taskInfo.setStatus(status);
        taskInfo.setTotalTime();
        manager.setTaskInfo(taskInfo);
    }
}
