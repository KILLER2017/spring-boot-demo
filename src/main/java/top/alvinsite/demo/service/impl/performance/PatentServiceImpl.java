package top.alvinsite.demo.service.impl.performance;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.performance.PatentDao;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.dto.performance.ResearcherPerformance;
import top.alvinsite.demo.model.entity.performance.Patent;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.ScoreDistributionParam;
import top.alvinsite.demo.service.ScoreDistributionService;
import top.alvinsite.demo.service.performance.PatentService;
import top.alvinsite.demo.service.rule.PatentRuleService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatentServiceImpl extends ServiceImpl<PatentDao, Patent> implements PatentService {
    @Autowired
    private RedisTemplate redisTemplate;
    private final static String performance = "patent";

    @Autowired
    private PatentRuleService patentRuleService;

    @Autowired
    protected ScoreDistributionService scoreDistributionService;

    @Override
    public List<Patent> findAll(PerformanceQuery performanceQuery) {
        List<Patent> list = baseMapper.findPatent(performanceQuery);
        list.stream()
                .map(this::getAnnualYear)
                .map(this::getProjectMemberNum)
                .map(this::getOrder)
                .map(this::calcProjectPoints)
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public Patent getAnnualYear(Patent project) {
        return project;
    }

    @Override
    public Patent getProjectMemberNum(Patent project) {
        // 专利信息已有总人数，无需再次计算
        return project;
    }

    @Override
    public Patent getOrder(Patent project) {
        String key = String.format("%s-%s-%s", performance, project.getId(), project.getAccount());
        Integer order = (Integer) redisTemplate.opsForValue().get(key);
        if (order == null) {
            order = calcOrder(project);
            redisTemplate.opsForValue().set(key, order);
        }
        project.setSignedOrder(order);
        return project;
    }


    private Integer calcOrder(Patent project) {
        int order = 1;
        for (ManagerUserDTO item : project.getInventors()) {
            if (item.getAccount().equals(project.getAccount())) {
                break;
            } else {
                order++;
            }
        }
        return order;
    }

    @Override
    public Patent calcProjectPoints(Patent project) {
        float score = patentRuleService.getScore(project);

        // 分值分配法
        score *= scoreDistributionService.getProportion(ScoreDistributionParam.build(project, performance));

        // 返回个人得分
        project.setScore(score);
        return project;
    }

    @Override
    public void setTotalPoints(ResearcherPerformance researcherPerformance, float totalPoints) {
        researcherPerformance.setPatentPoint(totalPoints);
    }
}
