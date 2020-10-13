package top.alvinsite.demo.service.rule;

import com.baomidou.mybatisplus.extension.service.IService;
import top.alvinsite.demo.model.entity.rule.ScoreDistributionConfig;

public interface ScoreDistributionConfigService extends IService<ScoreDistributionConfig> {
    Boolean useScoreDistribute(Integer year, String department, String performance);
}
