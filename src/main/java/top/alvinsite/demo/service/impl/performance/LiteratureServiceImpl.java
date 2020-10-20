package top.alvinsite.demo.service.impl.performance;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.performance.LiteratureDao;
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

    private final static String performance = "literature";

    @Autowired
    private LiteratureRuleService literatureRuleService;

    @Autowired
    protected ScoreDistributionService scoreDistributionService;

    @Override
    public List<Literature> findAll(PerformanceQuery performanceQuery) {
        return getBaseMapper().findLiterature(performanceQuery).stream().map(this::getProjectMemberNum).map(this::calcProjectPoints).collect(Collectors.toList());
    }

    @Override
    public Literature getProjectMemberNum(Literature project) {
        return project;
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
