package top.alvinsite.framework.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by shirukai on 2018/7/31
 * 异步任务执行器
 */

@Slf4j
@Component
public class AsyncTaskExecutor {

    @Async
    public Object executor(AsyncTaskConstructor asyncTaskGenerator, String taskInfo) throws Exception {
        log.info("AsyncTaskExecutor is executing async task:{}", taskInfo);
        return asyncTaskGenerator.async();
    }
}
