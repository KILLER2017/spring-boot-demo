package top.alvinsite.demo.service.impl.performance;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.performance.PaperDao;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.dto.performance.ResearcherPerformance;
import top.alvinsite.demo.model.entity.performance.Paper;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.ScoreDistributionParam;
import top.alvinsite.demo.service.ScoreDistributionService;
import top.alvinsite.demo.service.performance.PaperService;
import top.alvinsite.demo.service.rule.PaperRuleService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaperServiceImpl extends ServiceImpl<PaperDao, Paper> implements PaperService {
    @Autowired
    private RedisTemplate redisTemplate;
    private final static String performance = "paper";

    @Autowired
    private PaperRuleService paperRuleService;

    @Autowired
    protected ScoreDistributionService scoreDistributionService;

    @Override
    public List<Paper> findAll(PerformanceQuery performanceQuery) {
        return baseMapper.findPaper(performanceQuery).stream()
                .map(this::getAnnualYear)
                .map(this::getProjectMemberNum)
                .map(this::getOrder)
                .map(this::calcProjectPoints)
                .collect(Collectors.toList());
    }

    @Override
    public Paper getAnnualYear(Paper project) {
        return project;
    }

    @Override
    public Paper getProjectMemberNum(Paper project) {
        return project;
    }

    @Override
    public Paper getOrder(Paper project) {
        String key = String.format("%s-%s-%s", performance, project.getId(), project.getAccount());
        Integer order = (Integer) redisTemplate.opsForValue().get(key);
        if (order == null) {
            order = calcOrder(project);
            redisTemplate.opsForValue().set(key, order);
        }
        project.setSignedOrder(order);
        return project;
    }


    private Integer calcOrder(Paper project) {
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
    public Paper calcProjectPoints(Paper project){
        float score = paperRuleService.getScore(project);

        // 分值分配法
        score *= scoreDistributionService.getProportion(ScoreDistributionParam.build(project, performance));

        // 返回个人得分
        project.setScore(score);
        return project;
    }

    @Override
    public void setTotalPoints(ResearcherPerformance researcherPerformance, float totalPoints) {
        researcherPerformance.setPaperPoint(totalPoints);
    }
}
