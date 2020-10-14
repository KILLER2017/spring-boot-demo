package top.alvinsite.demo.service.impl.performance;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.performance.AwardedDAO;
import top.alvinsite.demo.model.entity.performance.Awarded;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.service.performance.AwardedService;

import java.util.List;

@Service
public class AwardedServiceImpl extends ServiceImpl<AwardedDAO, Awarded> implements AwardedService {
    @Override
    public List<Awarded> findAll(PerformanceQuery performanceQuery) throws Exception {
        return getBaseMapper().findAwarded(performanceQuery);
    }

    @Override
    public Awarded getProjectMemberNum(Awarded project) {
        return null;
    }

    @Override
    public Awarded calcTotalPoints(Awarded project) {
        return null;
    }
}
