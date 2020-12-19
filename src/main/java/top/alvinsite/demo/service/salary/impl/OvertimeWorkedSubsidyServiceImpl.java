package top.alvinsite.demo.service.salary.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.salary.OvertimeWorkedSubsidyDao;
import top.alvinsite.demo.model.entity.salary.OvertimeWorkedSubsidy;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.service.salary.OvertimeWorkedSubsidyService;

import java.util.List;

/**
 * @author Alvin
 */
@Service
public class OvertimeWorkedSubsidyServiceImpl extends ServiceImpl<OvertimeWorkedSubsidyDao, OvertimeWorkedSubsidy> implements OvertimeWorkedSubsidyService {
    @Override
    public List<OvertimeWorkedSubsidy> findAll(PerformanceQuery query) {
        return null;
    }
}
