package top.alvinsite.demo.service.impl.performance;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.performance.PaperDao;
import top.alvinsite.demo.model.entity.performance.Paper;
import top.alvinsite.demo.model.entity.rule.CrossingProjectRule;
import top.alvinsite.demo.model.entity.rule.PaperRule;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.ScoreDistributionParam;
import top.alvinsite.demo.service.ScoreDistributionService;
import top.alvinsite.demo.service.performance.PaperService;
import top.alvinsite.demo.service.rule.PaperRuleService;
import top.alvinsite.demo.service.rule.ScoreDistributionConfigService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaperServiceImpl extends ServiceImpl<PaperDao, Paper> implements PaperService {
    private final static String performance = "paper";
    @Autowired
    private PaperRuleService paperRuleService;

    @Autowired
    protected ScoreDistributionService scoreDistributionService;

    @Autowired
    private ScoreDistributionConfigService scoreDistributionConfigService;

    @Override
    public List<Paper> findAll(PerformanceQuery performanceQuery) {
        List<Paper> list = baseMapper.findPaper(performanceQuery);
        list.stream().map(this::getProjectMemberNum).map(this::calcTotalPoints).collect(Collectors.toList());
        return list;
    }

    @Override
    public Paper getProjectMemberNum(Paper project) {
        return project;
    }

    @Override
    public Paper calcTotalPoints(Paper project){
        // 读取计分规则
        PaperRule rule = paperRuleService.findRule(project);

        // 计算项目总分
        float score = 0f;


        if (rule == null) {
            return project;
        } else {
            score = rule.getScore();
        }

        ScoreDistributionParam param = new ScoreDistributionParam(
                project.getDepartment(),
                this.performance,
                project.getApprovalProjectYear(),
                project.getMemberNum(), project.
                getSignedOrder());
        float proportion = scoreDistributionService.getProportion(param);

        score *= proportion;


        // 返回个人得分
        project.setScore(score);
        return project;
    }
}
