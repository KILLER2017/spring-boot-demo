package top.alvinsite.demo.service.salary.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.salary.JobSubsidyDao;
import top.alvinsite.demo.model.entity.salary.JobSubsidy;
import top.alvinsite.demo.model.param.PerformanceQuery;
import top.alvinsite.demo.service.salary.JobSubsidyService;

import java.util.List;

/**
 * @author Alvin
 */
@Service
public class JobSubsidyServiceImpl extends ServiceImpl<JobSubsidyDao, JobSubsidy> implements JobSubsidyService {
    @Override
    public List<JobSubsidy> findAll(PerformanceQuery query) {
        return baseMapper.findAll(query);
    }

    @Override
    public Double getUserJobSubsidy(PerformanceQuery query) {
        JobSubsidy jobSubsidy = baseMapper.selectOne(Wrappers.<JobSubsidy>lambdaQuery()
                .eq(JobSubsidy::getAccount, query.getAccount())
                .eq(JobSubsidy::getYear, query.getYear())
                .last("limit 1")
        );

        return jobSubsidy != null ? jobSubsidy.getSubsidy() : 0;
    }
}
