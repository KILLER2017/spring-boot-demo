package top.alvinsite.demo.service.rule;

import com.baomidou.mybatisplus.extension.service.IService;
import top.alvinsite.demo.model.entity.rule.ScoreDistributionConfig;

/**
 * 绩效分值分配服务接口
 * @author Alvin
 */
public interface ScoreDistributionConfigService extends IService<ScoreDistributionConfig> {
    Boolean useScoreDistribute(Integer year, String department, String performance);
    void applyConfig(ScoreDistributionConfig config);
    void copyConfig(String sourceDepartment, int sourceYear, String targetDepartment, int targetYear);
}
