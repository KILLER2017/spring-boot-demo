package top.alvinsite.demo.service.impl.performance;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.performance.CopyrightDao;
import top.alvinsite.demo.model.dto.performance.ResearcherPerformance;
import top.alvinsite.demo.model.entity.performance.Copyright;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.ScoreDistributionParam;
import top.alvinsite.demo.service.ScoreDistributionService;
import top.alvinsite.demo.service.performance.CopyrightService;
import top.alvinsite.demo.service.rule.CopyrightRuleService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CopyrightServiceImpl extends ServiceImpl<CopyrightDao, Copyright> implements CopyrightService {

    private final static String performance = "copyright";

    @Autowired
    private CopyrightRuleService copyrightRuleService;

    @Autowired
    protected ScoreDistributionService scoreDistributionService;

    @Override
    public List<Copyright> findAll(PerformanceQuery performanceQuery) {
        return getBaseMapper().findCopyright(performanceQuery).stream().map(this::getProjectMemberNum).map(this::calcProjectPoints).collect(Collectors.toList());
    }

    @Override
    public Copyright getProjectMemberNum(Copyright project) {
        return project;
    }

    @Override
    public Copyright calcProjectPoints(Copyright project) {
        // 读取计分规则
        float score = copyrightRuleService.getScore(project);

        // 分值分配法
        score *= scoreDistributionService.getProportion(ScoreDistributionParam.build(project, performance));

        // 返回个人得分
        project.setScore(score);
        return project;
    }

    @Override
    public void setTotalPoints(ResearcherPerformance researcherPerformance, float totalPoints) {
        researcherPerformance.setCopyrightPoint(totalPoints);
    }
}
