package top.alvinsite.demo.service.impl.performance;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.performance.PaperDao;
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

    private final static String performance = "paper";

    @Autowired
    private PaperRuleService paperRuleService;

    @Autowired
    protected ScoreDistributionService scoreDistributionService;

    @Override
    public List<Paper> findAll(PerformanceQuery performanceQuery) {
        return baseMapper.findPaper(performanceQuery).stream().map(this::getProjectMemberNum).map(this::calcProjectPoints).collect(Collectors.toList());
    }

    @Override
    public Paper getProjectMemberNum(Paper project) {
        return project;
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
