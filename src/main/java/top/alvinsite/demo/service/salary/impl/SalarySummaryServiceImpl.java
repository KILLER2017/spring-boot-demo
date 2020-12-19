package top.alvinsite.demo.service.salary.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.salary.SalarySummaryDao;
import top.alvinsite.demo.model.entity.salary.SalarySummary;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.service.salary.SalarySummaryService;

import java.util.List;

/**
 * @author Alvin
 */
@Service
public class SalarySummaryServiceImpl extends ServiceImpl<SalarySummaryDao, SalarySummary> implements SalarySummaryService {

    @Override
    public List<SalarySummary> findAll(PerformanceQuery query) {
        return baseMapper.findAll(query);
    }
}
