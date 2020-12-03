package top.alvinsite.demo.service.impl.performance;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.performance.AwardedDao;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
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
    @Autowired
    private RedisTemplate redisTemplate;
    private final static String performance = "awarded";

    @Autowired
    private AwardedRuleService awardedRuleService;

    @Autowired
    protected ScoreDistributionService scoreDistributionService;

    @Override
    public List<Awarded> findAll(PerformanceQuery performanceQuery) {
        return getBaseMapper().findAwarded(performanceQuery).stream()
                .map(this::getAnnualYear)
                .map(this::getProjectMemberNum)
                .map(this::getOrder)
                .map(this::calcProjectPoints)
                .collect(Collectors.toList());
    }

    @Override
    public Awarded getAnnualYear(Awarded project) {
        return project;
    }

    @Override
    public Awarded getProjectMemberNum(Awarded project) {
        return project;
    }

    @Override
    public Awarded getOrder(Awarded project) {
        String key = String.format("%s-%s-%s", performance, project.getId(), project.getAccount());
        Integer order = (Integer) redisTemplate.opsForValue().get(key);
        if (order == null) {
            order = calcOrder(project);
            redisTemplate.opsForValue().set(key, order);
        }
        project.setSignedOrder(order);
        return project;
    }


    private Integer calcOrder(Awarded project) {
        int order = 1;
        for (ManagerUserDTO item : project.getAwardee()) {
            if (item.getAccount().equals(project.getAccount())) {
                break;
            } else {
                order++;
            }
        }
        return order;
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
