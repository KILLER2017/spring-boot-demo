package top.alvinsite.demo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.rule.ScoreDistributionDao;
import top.alvinsite.demo.model.entity.rule.ScoreDistribution;
import top.alvinsite.demo.model.params.ScoreDistributionParam;
import top.alvinsite.demo.service.ScoreDistributionService;

@Service
public class ScoreDistributionImpl extends ServiceImpl<ScoreDistributionDao, ScoreDistribution> implements ScoreDistributionService {

    @Override
    public float getProportion(ScoreDistributionParam scoreDistributionParam) {
        ScoreDistribution scoreDistribution = baseMapper.selectOne(
                Wrappers.<ScoreDistribution>lambdaQuery()
                        .eq(ScoreDistribution::getYear, scoreDistributionParam.getYear())
                        .eq(ScoreDistribution::getTotals, scoreDistributionParam.getTotals())
                        .eq(ScoreDistribution::getPosition, scoreDistributionParam.getPosition())
        );

        Assert.notNull(scoreDistribution, String.format("没有年份：%s，人数%s，顺序：%s的分值分配法。请先填写分值分配表，或选择不采用分值分配法",
                scoreDistributionParam.getYear(),
                scoreDistributionParam.getTotals(),
                scoreDistributionParam.getPosition()));
        return scoreDistribution.getProportion();
    }
}
