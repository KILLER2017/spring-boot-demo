package top.alvinsite.demo.service.impl.performance;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.performance.LiteratureDao;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.dto.performance.ResearcherPerformance;
import top.alvinsite.demo.model.entity.performance.Literature;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.ScoreDistributionParam;
import top.alvinsite.demo.service.ScoreDistributionService;
import top.alvinsite.demo.service.performance.LiteratureService;
import top.alvinsite.demo.service.rule.LiteratureRuleService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LiteratureServiceImpl extends ServiceImpl<LiteratureDao, Literature> implements LiteratureService {
    @Autowired
    private RedisTemplate redisTemplate;
    private final static String performance = "literature";

    @Autowired
    private LiteratureRuleService literatureRuleService;

    @Autowired
    protected ScoreDistributionService scoreDistributionService;

    @Override
    public List<Literature> findAll(PerformanceQuery performanceQuery) {
        List<Literature> list = getBaseMapper().findLiterature(performanceQuery);
        list.stream()
                .map(this::getAnnualYear)
                .map(this::getProjectMemberNum)
                .map(this::getOrder)
                .map(this::calcProjectPoints)
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public Literature getAnnualYear(Literature project) {
        return project;
    }

    @Override
    public Literature getProjectMemberNum(Literature project) {
        project.setMemberNum(project.getAuthors().size());
        return project;
    }

    @Override
    public Literature getOrder(Literature project) {
        String key = String.format("%s-%s-%s", performance, project.getId(), project.getAccount());
        Integer order = (Integer) redisTemplate.opsForValue().get(key);
        if (order == null) {
            order = calcOrder(project);
            redisTemplate.opsForValue().set(key, order);
        }
        project.setSignedOrder(order);
        return project;
    }


    private Integer calcOrder(Literature project) {
        int order = 1;
        for (ManagerUserDTO item : project.getAuthors()) {
            if (item.getAccount().equals(project.getAccount())) {
                break;
            } else {
                order++;
            }
        }
        return order;
    }

    @Override
    public Literature calcProjectPoints(Literature project) {
        // 读取计分规则
        float score = literatureRuleService.getScore(project);

        // 分值分配法
        score *= scoreDistributionService.getProportion(ScoreDistributionParam.build(project, performance));

        // 返回个人得分
        project.setScore(score);
        return project;
    }

    @Override
    public void setTotalPoints(ResearcherPerformance researcherPerformance, float totalPoints) {
        researcherPerformance.setLiteraturePoint(totalPoints);
    }
}
