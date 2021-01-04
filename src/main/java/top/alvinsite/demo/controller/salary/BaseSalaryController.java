package top.alvinsite.demo.controller.salary;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.RequestBody;
import top.alvinsite.demo.model.entity.salary.BaseSalaryModel;
import top.alvinsite.demo.service.salary.SalaryService;

import static top.alvinsite.utils.BeanUtils.transformFrom;

/**
 * @author Alvin
 * @email 54304634@qq.com
 * @date 2021/1/4 11:48
 */
public abstract class BaseSalaryController<M extends SalaryService<T>, T extends BaseSalaryModel, V> extends AbstractSalaryController<M, T, V> {

    @Override
    public void update(@RequestBody V record) {
        T item = transformFrom(record, getEntityClass());
        assert item != null;
        QueryWrapper<T> queryWrapper = new QueryWrapper<T>()
                .eq("account", item.getAccount())
                .eq("year", item.getYear());
        baseService.saveOrUpdate(item, queryWrapper);
    }

    @Override
    public void updateByPost(@RequestBody V record) {
        update(record);
    }
}
