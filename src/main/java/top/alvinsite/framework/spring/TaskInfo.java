package top.alvinsite.framework.spring;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by shirukai on 2018/7/31
 * 任务信息
 */

@Data
public class TaskInfo implements Serializable {
    private String taskId;
    private TaskStatusEnum status;
    private Date startTime;
    private Date endTime;
    private String totalTime;

    private Object result;
    private String message;

    public void setTotalTime() {
        this.totalTime = (this.endTime.getTime() - this.startTime.getTime()) + "ms";
    }
}
