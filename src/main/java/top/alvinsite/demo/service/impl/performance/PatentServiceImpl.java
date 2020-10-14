package top.alvinsite.demo.service.impl.performance;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.performance.PatentDao;
import top.alvinsite.demo.model.entity.performance.Patent;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.service.performance.PatentService;

import java.util.List;

@Service
public class PatentServiceImpl extends ServiceImpl<PatentDao, Patent> implements PatentService {
    @Override
    public List<Patent> findAll(PerformanceQuery performanceQuery) throws Exception {
        return getBaseMapper().findPatent(performanceQuery);
    }

    @Override
    public Patent getProjectMemberNum(Patent project) {
        return null;
    }

    @Override
    public Patent calcTotalPoints(Patent project) {
        return null;
    }
}
