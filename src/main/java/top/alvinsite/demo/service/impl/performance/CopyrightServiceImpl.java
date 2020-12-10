package top.alvinsite.demo.service.impl.performance;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.performance.CopyrightDao;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.dto.performance.ResearcherPerformance;
import top.alvinsite.demo.model.entity.performance.Copyright;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.ScoreDistributionParam;
import top.alvinsite.demo.service.ScoreDistributionService;
import top.alvinsite.demo.service.performance.CopyrightService;
import top.alvinsite.demo.service.rule.CopyrightRuleService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CopyrightServiceImpl extends ServiceImpl<CopyrightDao, Copyright> implements CopyrightService {

    @Autowired
    private RedisTemplate redisTemplate;

    private final static String performance = "copyright";

    @Autowired
    private CopyrightRuleService copyrightRuleService;

    @Autowired
    protected ScoreDistributionService scoreDistributionService;

    @Override
    public List<Copyright> findAll(PerformanceQuery performanceQuery) {
        List<Copyright> list = getBaseMapper().findCopyright(performanceQuery);
        list.stream()
                .map(this::getAnnualYear)
                .map(this::getProjectMemberNum)
                .map(this::getOrder)
                .map(this::calcProjectPoints)
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public Copyright getAnnualYear(Copyright project) {
        return project;
    }

    @Override
    public Copyright getOrder(Copyright project) {
        String key = String.format("%s-%s-%s", performance, project.getId(), project.getAccount());
        Integer order = (Integer) redisTemplate.opsForValue().get(key);
        if (order == null) {
            order = calcOrder(project);
            redisTemplate.opsForValue().set(key, order);
        }
        project.setSignedOrder(order);
        return project;
    }


    private Integer calcOrder(Copyright project) {
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
    public Copyright getProjectMemberNum(Copyright project) {
        project.setMemberNum(project.getAuthors().size());
        return project;
    }

    @Override
    public Copyright calcProjectPoints(Copyright project) {
        // 读取计分规则
        float score = copyrightRuleService.getScore(project);

        // 分值分配法
        score *= scoreDistributionService.getProportion(ScoreDistributionParam.build(project, performance));

        // 返回个人得分
        project.setScore(score);
        return project;
    }

    @Override
    public void setTotalPoints(ResearcherPerformance researcherPerformance, float totalPoints) {
        researcherPerformance.setCopyrightPoint(totalPoints);
    }
}
