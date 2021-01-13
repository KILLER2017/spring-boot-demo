package top.alvinsite.demo.service.rule.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.rule.ScoreDistributionDao;
import top.alvinsite.demo.model.entity.rule.ScoreDistribution;
import top.alvinsite.demo.model.param.ScoreDistributionParam;
import top.alvinsite.demo.service.rule.ScoreDistributionService;
import top.alvinsite.demo.service.rule.ScoreDistributionConfigService;

@Service
public class ScoreDistributionImpl extends ServiceImpl<ScoreDistributionDao, ScoreDistribution> implements ScoreDistributionService {

    @Autowired
    private ScoreDistributionConfigService scoreDistributionConfigService;

    @Override
    public boolean useScoreDistribute(ScoreDistributionParam scoreDistributionParam) {
        return scoreDistributionConfigService.useScoreDistribute(
                scoreDistributionParam.getYear(),
                scoreDistributionParam.getDepartment(),
                scoreDistributionParam.getPerformance()
        );
    }

    @Override
    public float getProportion(ScoreDistributionParam scoreDistributionParam) {
        // 查询是否采用分值分配法
        boolean useScoreDistribute = useScoreDistribute(scoreDistributionParam);
        if (!useScoreDistribute) {
            return 1;
        }

        ScoreDistribution scoreDistribution = baseMapper.selectOne(
                Wrappers.<ScoreDistribution>lambdaQuery()
                        .eq(ScoreDistribution::getTotals, scoreDistributionParam.getTotals())
                        .eq(ScoreDistribution::getPosition, scoreDistributionParam.getPosition())
        );

        if (scoreDistribution == null) {
            scoreDistribution = baseMapper.selectOne(
                    Wrappers.<ScoreDistribution>lambdaQuery()
                            .eq(ScoreDistribution::getTotals, 15)
                            .eq(ScoreDistribution::getPosition, scoreDistributionParam.getPosition())
            );
        }

        if (scoreDistribution == null) {
            String errorMessage = String.format("%s：没有人数%s，顺序：%s的分值分配法。请先填写分值分配表，或选择不采用分值分配法",
                    // 去掉年份筛选
                    scoreDistributionParam.getPerformance(),
                    scoreDistributionParam.getTotals(),
                    scoreDistributionParam.getPosition());
            log.error(errorMessage);
            return 0;
        }

        return scoreDistribution.getProportion();
    }
}
