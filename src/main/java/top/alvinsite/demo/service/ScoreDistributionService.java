package top.alvinsite.demo.service;

import top.alvinsite.demo.model.params.ScoreDistributionParam;

public interface ScoreDistributionService {
    boolean useScoreDistribute(ScoreDistributionParam scoreDistributionParam);
    float getProportion(ScoreDistributionParam scoreDistributionParam);
}
