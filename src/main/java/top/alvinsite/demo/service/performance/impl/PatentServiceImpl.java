package top.alvinsite.demo.service.performance.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.performance.PatentDao;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.dto.performance.ResearcherPerformance;
import top.alvinsite.demo.model.entity.performance.Patent;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.ScoreDistributionParam;
import top.alvinsite.demo.service.performance.PatentService;
import top.alvinsite.demo.service.rule.PatentRuleService;

import java.util.List;

@Service
public class PatentServiceImpl extends AbstractPerformanceService<PatentDao, Patent> implements PatentService {

    private final static String PERFORMANCE = "patent";

    @Autowired
    private PatentRuleService patentRuleService;

    @Override
    protected String getPerformance() {
        return PERFORMANCE;
    }

    @Override
    protected List<ManagerUserDTO> getMembers(Patent project) {
        return project.getInventors();
    }

    @Override
    public Patent calcProjectPoints(Patent project) {
        if (!isUniformDepartment(project)) {
            project.setScore(0);
            return project;
        }

        float score = patentRuleService.getScore(project);

        // 分值分配法
        score *= scoreDistributionService.getProportion(ScoreDistributionParam.build(project, PERFORMANCE));

        // 返回个人得分
        project.setScore(score);
        return project;
    }

    @Override
    protected List<Patent> beforeFindAll(PerformanceQuery performanceQuery) {
        return baseMapper.findPatent(performanceQuery);
    }

    @Override
    public void setTotalPoints(ResearcherPerformance researcherPerformance, float totalPoints) {
        researcherPerformance.setPatentPoint(totalPoints);
    }
}
