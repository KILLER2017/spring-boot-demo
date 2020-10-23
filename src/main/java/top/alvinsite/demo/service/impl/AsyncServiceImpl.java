package top.alvinsite.demo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.service.AsyncService;

@Slf4j
@Service
public class AsyncServiceImpl implements AsyncService {
    @Override
    @Async
    public void generateExcel() {
        log.info("报表线程名称：【{}】", Thread.currentThread().getName());
    }
}
