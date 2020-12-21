package top.alvinsite.demo.service.performance.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.performance.PaperDao;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.dto.performance.ResearcherPerformance;
import top.alvinsite.demo.model.entity.performance.Paper;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.ScoreDistributionParam;
import top.alvinsite.demo.service.performance.PaperService;
import top.alvinsite.demo.service.rule.PaperRuleService;

import java.util.List;

@Service
public class PaperServiceImpl extends AbstractPerformanceService<PaperDao, Paper> implements PaperService {
    private final static String PERFORMANCE = "paper";

    @Autowired
    private PaperRuleService paperRuleService;

    @Override
    protected String getPerformance() {
        return PERFORMANCE;
    }

    @Override
    protected List<ManagerUserDTO> getMembers(Paper project) {
        return project.getAuthors();
    }

    @Override
    protected List<Paper> beforeFindAll(PerformanceQuery performanceQuery) {
        return baseMapper.findPaper(performanceQuery);
    }

    @Override
    public Paper calcProjectPoints(Paper project){
        float score = paperRuleService.getScore(project);

        // 分值分配法
        score *= scoreDistributionService.getProportion(ScoreDistributionParam.build(project, PERFORMANCE));

        // 返回个人得分
        project.setScore(score);
        return project;
    }

    @Override
    public void setTotalPoints(ResearcherPerformance researcherPerformance, float totalPoints) {
        researcherPerformance.setPaperPoint(totalPoints);
    }
}
