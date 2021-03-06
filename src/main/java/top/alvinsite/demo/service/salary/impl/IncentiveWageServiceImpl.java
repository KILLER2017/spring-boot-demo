package top.alvinsite.demo.service.salary.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.salary.IncentiveWageDao;
import top.alvinsite.demo.model.entity.salary.IncentiveWage;
import top.alvinsite.demo.model.param.PerformanceQuery;
import top.alvinsite.demo.service.salary.IncentiveWageService;

import java.util.List;

/**
 * @author Alvin
 */
@Service
public class IncentiveWageServiceImpl extends ServiceImpl<IncentiveWageDao, IncentiveWage> implements IncentiveWageService {
    @Override
    public List<IncentiveWage> findAll(PerformanceQuery query) {
        return baseMapper.findAll(query);
    }

    @Override
    public Double getUserIncentiveWage(PerformanceQuery query) {
        return baseMapper.getUserIncentiveWage(query);
    }
}
