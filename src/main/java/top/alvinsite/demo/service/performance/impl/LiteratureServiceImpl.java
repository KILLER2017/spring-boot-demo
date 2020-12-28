package top.alvinsite.demo.service.performance.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.performance.LiteratureDao;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.dto.performance.ResearcherPerformance;
import top.alvinsite.demo.model.entity.performance.Literature;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.ScoreDistributionParam;
import top.alvinsite.demo.service.performance.LiteratureService;
import top.alvinsite.demo.service.rule.LiteratureRuleService;

import java.util.List;

@Service
public class LiteratureServiceImpl extends AbstractPerformanceService<LiteratureDao, Literature> implements LiteratureService {

    private final static String PERFORMANCE = "literature";

    @Autowired
    private LiteratureRuleService literatureRuleService;

    @Override
    protected String getPerformance() {
        return PERFORMANCE;
    }

    @Override
    protected List<ManagerUserDTO> getMembers(Literature project) {
        return project.getAuthors();
    }

    @Override
    public Literature calcProjectPoints(Literature project) {
        if (!isUniformDepartment(project)) {
            project.setScore(0);
            return project;
        }

        // 读取计分规则
        float score = literatureRuleService.getScore(project);

        // 分值分配法
        score *= scoreDistributionService.getProportion(ScoreDistributionParam.build(project, PERFORMANCE));

        // 返回个人得分
        project.setScore(score);
        return project;
    }

    @Override
    protected List<Literature> beforeFindAll(PerformanceQuery performanceQuery) {
        return baseMapper.findLiterature(performanceQuery);
    }

    @Override
    public void setTotalPoints(ResearcherPerformance researcherPerformance, float totalPoints) {
        researcherPerformance.setLiteraturePoint(totalPoints);
    }
}
