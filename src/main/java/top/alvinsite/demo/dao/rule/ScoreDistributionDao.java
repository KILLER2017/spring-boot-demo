package top.alvinsite.demo.dao.rule;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.entity.rule.ScoreDistribution;
import top.alvinsite.demo.model.params.ScoreDistributionParam;

@Repository
public interface ScoreDistributionDao extends BaseMapper<ScoreDistribution> {
    Float findOne(ScoreDistributionParam scoreDistributionParam);
}
