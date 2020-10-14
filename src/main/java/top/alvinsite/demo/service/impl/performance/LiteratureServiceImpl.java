package top.alvinsite.demo.service.impl.performance;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.performance.LiteratureDao;
import top.alvinsite.demo.model.entity.performance.Literature;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.service.performance.LiteratureService;

import java.util.List;

@Service
public class LiteratureServiceImpl extends ServiceImpl<LiteratureDao, Literature> implements LiteratureService {
    @Override
    public List<Literature> findAll(PerformanceQuery performanceQuery) throws Exception {
        return getBaseMapper().findLiterature(performanceQuery);
    }

    @Override
    public Literature getProjectMemberNum(Literature project) {
        return null;
    }

    @Override
    public Literature calcTotalPoints(Literature project) {
        return null;
    }
}
