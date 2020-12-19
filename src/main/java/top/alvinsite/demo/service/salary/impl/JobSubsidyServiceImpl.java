package top.alvinsite.demo.service.salary.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.salary.JobSubsidyDao;
import top.alvinsite.demo.model.entity.salary.JobSubsidy;
import top.alvinsite.demo.model.params.PerformanceQuery;
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
}
