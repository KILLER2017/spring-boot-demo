package top.alvinsite.demo.service.performance.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.performance.CopyrightDao;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.dto.performance.ResearcherPerformance;
import top.alvinsite.demo.model.entity.performance.Copyright;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.ScoreDistributionParam;
import top.alvinsite.demo.service.performance.CopyrightService;
import top.alvinsite.demo.service.rule.CopyrightRuleService;

import java.util.List;

@Service
public class CopyrightServiceImpl extends AbstractPerformanceService<CopyrightDao, Copyright> implements CopyrightService {

    private final static String PERFORMANCE = "copyright";

    @Autowired
    private CopyrightRuleService copyrightRuleService;

    @Override
    protected String getPerformance() {
        return PERFORMANCE;
    }

    @Override
    protected List<ManagerUserDTO> getMembers(Copyright project) {
        return project.getAuthors();
    }

    @Override
    public Copyright calcProjectPoints(Copyright project) {
        if (!isUniformDepartment(project)) {
            project.setScore(0);
            return project;
        }

        // 读取计分规则
        double score = copyrightRuleService.getScore(project);

        // 分值分配法
        score *= scoreDistributionService.getProportion(ScoreDistributionParam.build(project, PERFORMANCE));

        // 返回个人得分
        project.setScore(score);
        return project;
    }

    @Override
    protected List<Copyright> beforeFindAll(PerformanceQuery performanceQuery) {
        return baseMapper.findCopyright(performanceQuery);
    }

    @Override
    public void setTotalPoints(ResearcherPerformance researcherPerformance, float totalPoints) {
        researcherPerformance.setCopyrightPoint(totalPoints);
    }
}
