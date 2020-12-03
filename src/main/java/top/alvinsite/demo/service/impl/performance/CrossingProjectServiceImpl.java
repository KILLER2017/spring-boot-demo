package top.alvinsite.demo.service.impl.performance;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.ProjectMemberDao;
import top.alvinsite.demo.dao.performance.ProjectDao;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.dto.performance.ResearcherPerformance;
import top.alvinsite.demo.model.entity.ProjectMember;
import top.alvinsite.demo.model.entity.performance.CrossingProject;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.ScoreDistributionParam;
import top.alvinsite.demo.service.ScoreDistributionService;
import top.alvinsite.demo.service.performance.CrossingProjectService;
import top.alvinsite.demo.service.rule.CrossingRuleService;
import top.alvinsite.demo.service.rule.ScoreDistributionConfigService;
import top.alvinsite.utils.TimeUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CrossingProjectServiceImpl implements CrossingProjectService {
    @Autowired
    private RedisTemplate redisTemplate;
    private final static String performance = "crossing";

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private ProjectMemberDao projectMemberDao;

    @Autowired
    private CrossingRuleService crossingRuleService;

    @Autowired
    private ScoreDistributionService scoreDistributionService;

    @Autowired
    private ScoreDistributionConfigService scoreDistributionConfigService;

    @Override
    public List<CrossingProject> findAll(PerformanceQuery performanceQuery){
        List<CrossingProject> list = projectDao.findCrossingProject(performanceQuery);
        list.stream()
                .map(this::getProjectMemberNum)
                .map(this::getOrder)
                .map(this::calcProjectPoints)
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public CrossingProject getAnnualYear(CrossingProject project) {
        return project;
    }

    @Override
    public CrossingProject getOrder(CrossingProject project) {
        String key = String.format("%s-%s-%s", performance, project.getId(), project.getAccount());
        Integer order = (Integer) redisTemplate.opsForValue().get(key);
        if (order == null) {
            order = calcOrder(project);
            redisTemplate.opsForValue().set(key, order);
        }
        project.setSignedOrder(order);
        return project;
    }


    private Integer calcOrder(CrossingProject project) {
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
    public CrossingProject getProjectMemberNum(CrossingProject project) {
        Integer memberNum = projectMemberDao.selectCount(Wrappers.<ProjectMember>lambdaQuery().eq(ProjectMember::getProjectNum, project.getProjectNum()));
        project.setMemberNum(memberNum);
        return project;
    }


    @Override
    public CrossingProject calcProjectPoints(CrossingProject project) {
        // 读取计分规则
        float score = crossingRuleService.getScore(project);

        float proportion = scoreDistributionService.getProportion(ScoreDistributionParam.build(project, performance));
        int annualNum = TimeUtils.getAnnualNum(project.getStartedTime(), project.getStartedTime());
        // 返回个人得分
        project.setBudgetScore(project.getBudgetScore() * proportion / annualNum);
        project.setProjectScore(project.getProjectScore() * proportion / annualNum);
        project.setScore(score * proportion / annualNum);
        return project;
    }

    @Override
    public void setTotalPoints(ResearcherPerformance researcherPerformance, float totalPoints) {
        researcherPerformance.setCrossingPoint(totalPoints);
    }
}
