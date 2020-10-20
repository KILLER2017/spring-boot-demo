package top.alvinsite.demo.service.impl.performance;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.ProjectMemberDao;
import top.alvinsite.demo.dao.performance.ProjectDao;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.dto.performance.ResearcherPerformance;
import top.alvinsite.demo.model.entity.ProjectMember;
import top.alvinsite.demo.model.entity.performance.LongitudinalProject;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.ScoreDistributionParam;
import top.alvinsite.demo.service.ScoreDistributionService;
import top.alvinsite.demo.service.performance.LongitudinalProjectService;
import top.alvinsite.demo.service.rule.LongitudinalRuleService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LongitudinalProjectServiceImpl implements LongitudinalProjectService {
    private final static String performance = "longitudinal";

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private ProjectMemberDao projectMemberDao;
    @Autowired
    private LongitudinalRuleService longitudinalRuleService;

    @Autowired
    private ScoreDistributionService scoreDistributionService;


    @Override
    public List<LongitudinalProject> findAll(PerformanceQuery performanceQuery) {
        List<LongitudinalProject> list = projectDao.findLongitudinalProject(performanceQuery);
        list.stream().map(this::getProjectMemberNum).map(this::calcProjectPoints).collect(Collectors.toList());
        return list;
    }

    @Override
    public LongitudinalProject getProjectMemberNum(LongitudinalProject project) {
        Integer memberNum = projectMemberDao.selectCount(Wrappers.<ProjectMember>lambdaQuery().eq(ProjectMember::getProjectNum, project.getProjectNum()));
        project.setMemberNum(memberNum);
        return project;
    }

    @Override
    public LongitudinalProject calcProjectPoints(LongitudinalProject project) {
        float score = longitudinalRuleService.getScore(project);

        float proportion = scoreDistributionService.getProportion(ScoreDistributionParam.build(project, performance));

        // 返回个人得分
        project.setBudgetScore(project.getBudgetScore() * proportion);
        project.setProjectScore(project.getProjectScore() * proportion);
        project.setScore(score * proportion);
        return project;
    }

    @Override
    public void setTotalPoints(ResearcherPerformance researcherPerformance, float totalPoints) {
        researcherPerformance.setLiteraturePoint(totalPoints);
    }

}
