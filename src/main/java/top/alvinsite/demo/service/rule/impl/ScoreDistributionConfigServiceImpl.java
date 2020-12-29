package top.alvinsite.demo.service.rule.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.rule.ScoreDistributionConfigDao;
import top.alvinsite.demo.model.entity.rule.ScoreDistributionConfig;
import top.alvinsite.demo.service.rule.ScoreDistributionConfigService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alvin
 */
@Service
public class ScoreDistributionConfigServiceImpl extends ServiceImpl<ScoreDistributionConfigDao, ScoreDistributionConfig> implements ScoreDistributionConfigService {

    @Autowired
    private ScoreDistributionConfigDao scoreDistributionConfigDao;

    @Override
    public Boolean useScoreDistribute(Integer year, String department, String performance) {
        ScoreDistributionConfig config = scoreDistributionConfigDao.selectOne(Wrappers.<ScoreDistributionConfig>lambdaQuery()
                .eq(ScoreDistributionConfig::getYear, year)
                .eq(ScoreDistributionConfig::getDepartment, department)
                .eq(ScoreDistributionConfig::getPerformance, performance)
        );

        return config == null ? false : config.getActive();
    }

    @Override
    public void applyConfig(ScoreDistributionConfig newConfig) {
        LambdaQueryWrapper<ScoreDistributionConfig> query = Wrappers.<ScoreDistributionConfig>lambdaQuery()
                .eq(ScoreDistributionConfig::getDepartment, newConfig.getDepartment())
                .eq(ScoreDistributionConfig::getYear, newConfig.getYear())
                .eq(ScoreDistributionConfig::getPerformance, newConfig.getPerformance());

        ScoreDistributionConfig oldConfig = scoreDistributionConfigDao.selectOne(query);

        if (oldConfig != null) {
            scoreDistributionConfigDao.update(newConfig, query);
        } else {
            scoreDistributionConfigDao.insert(newConfig);
        }
    }

    @Override
    public void copyConfig(String sourceDepartment, int sourceYear, String targetDepartment, int targetYear) {
        List<ScoreDistributionConfig> configs = list(Wrappers.<ScoreDistributionConfig>lambdaQuery()
                .eq(ScoreDistributionConfig::getDepartment, sourceDepartment)
                .eq(ScoreDistributionConfig::getYear, sourceYear));

        configs = configs.stream().map(item -> {
            item.setDepartment(targetDepartment);
            item.setYear(targetYear);
            return item;
        }).collect(Collectors.toList());

        remove(Wrappers.<ScoreDistributionConfig>lambdaQuery()
                .eq(ScoreDistributionConfig::getDepartment, targetDepartment)
                .eq(ScoreDistributionConfig::getYear, targetYear));

        saveBatch(configs);
    }
}
