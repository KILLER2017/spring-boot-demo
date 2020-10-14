package top.alvinsite.demo.service.impl.performance;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.performance.CopyrightDao;
import top.alvinsite.demo.model.entity.performance.Copyright;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.service.performance.CopyrightService;

import java.util.List;

@Service
public class CopyrightServiceImpl extends ServiceImpl<CopyrightDao, Copyright> implements CopyrightService {
    @Override
    public List<Copyright> findAll(PerformanceQuery performanceQuery) throws Exception {
        return getBaseMapper().findCopyright(performanceQuery);
    }

    @Override
    public Copyright getProjectMemberNum(Copyright project) {
        return null;
    }

    @Override
    public Copyright calcTotalPoints(Copyright project) {
        return null;
    }
}
