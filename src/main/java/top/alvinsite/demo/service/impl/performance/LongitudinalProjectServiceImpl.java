package top.alvinsite.demo.service.impl.performance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.ProjectMemberDao;
import top.alvinsite.demo.dao.performance.ProjectDao;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.dto.performance.ResearcherPerformance;
import top.alvinsite.demo.model.entity.performance.LongitudinalProject;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.ScoreDistributionParam;
import top.alvinsite.demo.service.ScoreDistributionService;
import top.alvinsite.demo.service.performance.LongitudinalProjectService;
import top.alvinsite.demo.service.rule.LongitudinalRuleService;
import top.alvinsite.utils.TimeUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LongitudinalProjectServiceImpl implements LongitudinalProjectService {
    @Autowired
    private RedisTemplate redisTemplate;

    private final static String performance = "longitudinal";

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private ProjectMemberDao projectMemberDao;
    @Autowired
    private LongitudinalRuleService longitudinalRuleService;

    @Autowired
    private ScoreDistributionService scoreDistributionService;


    @Override
    public List<LongitudinalProject> findAll(PerformanceQuery performanceQuery) {
        List<LongitudinalProject> list = projectDao.findLongitudinalProject(performanceQuery);
        list.stream()
                .map(this::getProjectMemberNum)
                .map(this::getOrder)
                .map(this::calcProjectPoints)
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public LongitudinalProject getAnnualYear(LongitudinalProject project) {
        return project;
    }

    @Override
    public LongitudinalProject getProjectMemberNum(LongitudinalProject project) {
        int memberNum = project.getMembers().size();
        project.setMemberNum(memberNum);
        return project;
    }

    @Override
    public LongitudinalProject getOrder(LongitudinalProject project) {
        String key = String.format("%s-%s-%s", performance, project.getId(), project.getAccount());
        Integer order = (Integer) redisTemplate.opsForValue().get(key);
        if (order == null) {
            order = calcOrder(project);
            redisTemplate.opsForValue().set(key, order);
        }
        project.setSignedOrder(order);
        return project;
    }


    private Integer calcOrder(LongitudinalProject project) {
        int order = 1;
        for (ManagerUserDTO item : project.getMembers()) {
            if (item.getAccount().equals(project.getAccount())) {
                break;
            } else {
                order++;
            }
        }
        return order;
    }

    @Override
    public LongitudinalProject calcProjectPoints(LongitudinalProject project) {
        float score = longitudinalRuleService.getScore(project);

        float proportion = scoreDistributionService.getProportion(ScoreDistributionParam.build(project, performance));
        // 项目年度数
        int annualNum = TimeUtils.getAnnualNum(project.getStartedTime(), project.getFinishedTime());


        // 返回个人得分
        project.setBudgetScore(project.getBudgetScore() * proportion / annualNum);
        project.setProjectScore(project.getProjectScore() * proportion / annualNum);
        project.setScore(score * proportion / annualNum);
        return project;
    }

    @Override
    public void setTotalPoints(ResearcherPerformance researcherPerformance, float totalPoints) {
        researcherPerformance.setLongitudinalPoint(totalPoints);
    }

}
