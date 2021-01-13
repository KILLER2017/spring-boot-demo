package top.alvinsite.demo.service.salary;

import com.baomidou.mybatisplus.extension.service.IService;
import top.alvinsite.demo.model.param.PerformanceQuery;

import java.util.List;

/**
 * @author Alvin
 */
public interface SalaryService<T> extends IService<T> {

    /**
     * 获取列表数据
     * @param query 查询参数
     * @return 列表数据
     */
    List<T> findAll(PerformanceQuery query);
}
