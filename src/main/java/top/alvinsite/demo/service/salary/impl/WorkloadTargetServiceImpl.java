package top.alvinsite.demo.service.salary.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.salary.WorkloadTargetDao;
import top.alvinsite.demo.model.entity.salary.WorkloadTarget;
import top.alvinsite.demo.model.param.PerformanceQuery;
import top.alvinsite.demo.model.param.WorkloadTargetParam;
import top.alvinsite.demo.service.salary.WorkloadTargetService;

import java.util.List;

/**
 * @author Alvin
 */
@Service
public class WorkloadTargetServiceImpl extends ServiceImpl<WorkloadTargetDao, WorkloadTarget> implements WorkloadTargetService {
    @Override
    public WorkloadTarget getOne(WorkloadTargetParam param) {
        return this.getOne(Wrappers.<WorkloadTarget>lambdaQuery()
                        .eq(WorkloadTarget::getYear, param.getYear())
                        .eq(WorkloadTarget::getDepartment, param.getDepartment())
                        .eq(WorkloadTarget::getLevel, param.getLevel())
                        .eq(WorkloadTarget::getType, param.getType()),
                false
        );
    }

    @Override
    public List<WorkloadTarget> findAll(PerformanceQuery query) {
        return baseMapper.findAll(query);
    }
}
