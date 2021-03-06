package top.alvinsite.demo.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.model.param.Page;
import top.alvinsite.demo.model.param.PerformanceQuery;


@RestController
public interface IRestController<T> {
    @GetMapping
    PageInfo<T> get(Page page, PerformanceQuery performanceQuery) throws Exception;

    @PostMapping
    default String post() {
        return "this is post request";
    }
}
