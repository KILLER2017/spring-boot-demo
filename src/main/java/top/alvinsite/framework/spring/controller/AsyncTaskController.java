package top.alvinsite.framework.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.model.support.BaseResponse;
import top.alvinsite.framework.spring.AsyncTaskManager;
import top.alvinsite.framework.spring.TaskInfo;

@RestController
@RequestMapping(value = "/api/v1/asynctask")
public class AsyncTaskController {
    //注入异步任务管理器
    @Autowired
    AsyncTaskManager asyncTaskManager;

    @RequestMapping(value = "/startTask", method = RequestMethod.GET)
    public BaseResponse startAsyncTask() throws Exception {
        //调用任务管理器中的submit去提交一个异步任务
        TaskInfo taskInfo = asyncTaskManager.submit(() -> {
            System.out.println("__________");

                //模拟异步，睡眠6秒
                // Thread.sleep(30000);
                throw new Exception("这里发生了异常");

            // System.out.println("__________");
        });
        return BaseResponse.ok("操作成功", taskInfo);
    }

    @RequestMapping(value = "/getTaskStatus", method = RequestMethod.GET)
    public BaseResponse getTaskStatus(
            @RequestParam("taskId") String taskId) {
        return BaseResponse.ok("操作成功", asyncTaskManager.getTaskInfo(taskId));
    }
}
