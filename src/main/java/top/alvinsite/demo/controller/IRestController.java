package top.alvinsite.demo.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.model.params.Page;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.support.UserInfo;


@RestController
public interface IRestController<T> {
    @GetMapping
    PageInfo<T> get(@RequestHeader("authorization") UserInfo userInfo, Page page, PerformanceQuery performanceQuery) throws Exception;

    @PostMapping
    default String post() {
        return "this is post request";
    }
}
