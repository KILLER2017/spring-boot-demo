package top.alvinsite.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.model.support.BaseResponse;
import top.alvinsite.demo.service.AsyncService;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("async")
public class AsyncController {
    @Autowired
    private AsyncService asyncService;

    @GetMapping
    public BaseResponse index() {
        log.info("请求线程名称：【{}】", Thread.currentThread().getName());
        asyncService.generateExcel();

        Map entry = new HashMap<>();
        entry.put("target_url", "http://www.baidu.com");
        return BaseResponse.ok("正在导入中......", entry);
    }
}
