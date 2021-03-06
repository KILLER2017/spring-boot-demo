package top.alvinsite.demo.service.rule;

import com.baomidou.mybatisplus.extension.service.IService;
import top.alvinsite.demo.model.entity.rule.ScoreDistribution;
import top.alvinsite.demo.model.param.ScoreDistributionParam;

public interface ScoreDistributionService extends IService<ScoreDistribution> {
    boolean useScoreDistribute(ScoreDistributionParam scoreDistributionParam);
    float getProportion(ScoreDistributionParam scoreDistributionParam);
}
