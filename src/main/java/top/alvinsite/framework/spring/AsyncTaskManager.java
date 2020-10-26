package top.alvinsite.framework.spring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import top.alvinsite.demo.model.support.UserInfo;
import top.alvinsite.framework.spring.service.AsyncTaskConstructor;
import top.alvinsite.framework.spring.service.AsyncTaskExecutor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by shirukai on 2018/7/31
 * 异步任务管理器
 */
@Component
public class AsyncTaskManager {

    private Map<String, TaskInfo> taskContainer = new HashMap<>(16);

    @Autowired
    AsyncTaskExecutor asyncTaskExecutor;


    /**
     * 初始化任务
     *
     * @return taskInfo
     */
    public TaskInfo initTask() {
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setTaskId(getTaskId());
        taskInfo.setStatus(TaskStatusEnum.STARTED);
        taskInfo.setStartTime(new Date());
        setTaskInfo(taskInfo);
        return taskInfo;
    }

    /**
     * 初始化任务
     * @param asyncTaskConstructor 异步任务构造器
     * @return taskInfo
     */
    public TaskInfo submit(AsyncTaskConstructor asyncTaskConstructor) throws Exception {
        TaskInfo info = initTask();
        String taskId = info.getTaskId();
        asyncTaskExecutor.executor(asyncTaskConstructor,taskId);
        return info;
    }

    /**
     * 保存任务信息
     *
     * @param taskInfo 任务信息
     */
    public void setTaskInfo(TaskInfo taskInfo) {
        taskContainer.put(taskInfo.getTaskId(), taskInfo);
    }

    /**
     * 获取任务信息
     *
     * @param taskId 任务ID
     * @return
     */
    public TaskInfo getTaskInfo(String taskId) {
        return taskContainer.get(taskId);
    }

    /**
     * 获取任务状态
     *
     * @param taskId 任务ID
     * @return
     */
    public TaskStatusEnum getTaskStatus(String taskId) {
        return getTaskInfo(taskId).getStatus();
    }

    /**
     * 生成任务ID
     *
     * @return taskId
     */
    public String getTaskId() {
        return UUID.randomUUID().toString();
    }
}
