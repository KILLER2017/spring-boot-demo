package top.alvinsite.demo.service.impl.performance;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.performance.PatentDao;
import top.alvinsite.demo.model.dto.performance.ResearcherPerformance;
import top.alvinsite.demo.model.entity.performance.Patent;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.ScoreDistributionParam;
import top.alvinsite.demo.service.ScoreDistributionService;
import top.alvinsite.demo.service.performance.PatentService;
import top.alvinsite.demo.service.rule.PatentRuleService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatentServiceImpl extends ServiceImpl<PatentDao, Patent> implements PatentService {

    private final static String performance = "patent";

    @Autowired
    private PatentRuleService patentRuleService;

    @Autowired
    protected ScoreDistributionService scoreDistributionService;

    @Override
    public List<Patent> findAll(PerformanceQuery performanceQuery) {
        return baseMapper.findPatent(performanceQuery).stream().map(this::getProjectMemberNum).map(this::calcProjectPoints).collect(Collectors.toList());
    }

    @Override
    public Patent getProjectMemberNum(Patent project) {
        // 专利信息已有总人数，无需再次计算
        return project;
    }

    @Override
    public Patent calcProjectPoints(Patent project) {
        float score = patentRuleService.getScore(project);

        // 分值分配法
        score *= scoreDistributionService.getProportion(ScoreDistributionParam.build(project, performance));

        // 返回个人得分
        project.setScore(score);
        return project;
    }

    @Override
    public void setTotalPoints(ResearcherPerformance researcherPerformance, float totalPoints) {
        researcherPerformance.setPatentPoint(totalPoints);
    }
}
