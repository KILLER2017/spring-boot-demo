package top.alvinsite.demo.service.impl.rule;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.rule.ScoreDistributionConfigDao;
import top.alvinsite.demo.model.entity.rule.ScoreDistributionConfig;
import top.alvinsite.demo.service.rule.ScoreDistributionConfigService;

@Service
public class ScoreDistributionConfigServiceImpl extends ServiceImpl<ScoreDistributionConfigDao, ScoreDistributionConfig> implements ScoreDistributionConfigService {

    @Autowired
    private ScoreDistributionConfigDao scoreDistributionConfigDao;

    public Boolean useScoreDistribute(Integer year, String department, String performance) {
        ScoreDistributionConfig config = scoreDistributionConfigDao.selectOne(Wrappers.<ScoreDistributionConfig>lambdaQuery()
                .eq(ScoreDistributionConfig::getYear, year)
                .eq(ScoreDistributionConfig::getDepartment, department)
                .eq(ScoreDistributionConfig::getPerformance, performance)
        );

        return config == null ? false : config.getActive();
    }
}
