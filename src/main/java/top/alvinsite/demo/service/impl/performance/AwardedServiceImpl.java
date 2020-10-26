package top.alvinsite.demo.service.impl.performance;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.performance.AwardedDao;
import top.alvinsite.demo.model.dto.performance.ResearcherPerformance;
import top.alvinsite.demo.model.entity.performance.Awarded;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.ScoreDistributionParam;
import top.alvinsite.demo.service.ScoreDistributionService;
import top.alvinsite.demo.service.performance.AwardedService;
import top.alvinsite.demo.service.rule.AwardedRuleService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AwardedServiceImpl extends ServiceImpl<AwardedDao, Awarded> implements AwardedService {

    private final static String performance = "awarded";

    @Autowired
    private AwardedRuleService awardedRuleService;

    @Autowired
    protected ScoreDistributionService scoreDistributionService;

    @Override
    public List<Awarded> findAll(PerformanceQuery performanceQuery) {
        return getBaseMapper().findAwarded(performanceQuery).stream().map(this::getProjectMemberNum).map(this::calcProjectPoints).collect(Collectors.toList());
    }

    @Override
    public Awarded getProjectMemberNum(Awarded project) {
        return project;
    }

    @Override
    public Awarded calcProjectPoints(Awarded project) {
        float score = awardedRuleService.getScore(project);

        // 分值分配法
        score *= scoreDistributionService.getProportion(ScoreDistributionParam.build(project, performance));

        // 返回个人得分
        project.setScore(score);
        return project;
    }

    @Override
    public void setTotalPoints(ResearcherPerformance researcherPerformance, float totalPoints) {
        researcherPerformance.setAwardedPoint(totalPoints);
    }

}
