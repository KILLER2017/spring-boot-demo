package top.alvinsite.demo.service.rule.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.rule.PaperRuleDao;
import top.alvinsite.demo.model.entity.performance.Paper;
import top.alvinsite.demo.model.entity.rule.PaperRule;
import top.alvinsite.demo.model.entity.type.PaperType;
import top.alvinsite.demo.model.param.RuleQuery;
import top.alvinsite.demo.service.rule.PaperRuleService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Alvin
 */
@Slf4j
@Service
public class PaperRuleServiceImpl extends AbstractRuleService<PaperRuleDao, Paper, PaperRule> implements PaperRuleService {
    @Autowired
    private PaperRuleDao paperRuleDao;

    @Override
    public List<PaperRule> list(Wrapper<PaperRule> queryWrapper) {
        return paperRuleDao.findAllByWrapper(queryWrapper);
    }

    @Override
    public List<PaperRule> findAll(RuleQuery ruleQuery) {
        return paperRuleDao.findAll(ruleQuery);
    }


    @Override
    public PaperRule findRule(Paper project) {
        PaperRule rule = paperRuleDao.selectOne(Wrappers.<PaperRule>lambdaQuery()
                .eq(PaperRule::getYear, project.getApprovalProjectYear())
                .eq(PaperRule::getDepartment, project.getDepartment().getId())
                .eq(PaperRule::getType, "")
        );

        if (null == rule) {
            log.info("找不到对应的绩效规则， {}", project);
        }

        return rule;
    }


    public PaperRule findRule(Integer year, String department, String type) {
        PaperRule rule = paperRuleDao.selectOne(Wrappers.<PaperRule>lambdaQuery()
                .eq(PaperRule::getYear, year)
                .eq(PaperRule::getDepartment, department)
                .eq(PaperRule::getType, type)
        );

        if (null == rule) {
            log.info("找不到对应的绩效规则， {},{},{}", year, department, type);
        }

        return rule;
    }

    @Override
    public double getScore(Paper project) {
        // 读取计分规则
        List<Float> scores = new ArrayList<>();

        int year = project.getApprovalProjectYear();
        String department = project.getDepartment().getId();
        for (PaperType type : project.getPublicationType()) {
            PaperRule rule = findRule(year, department, type.getId());
            float score = rule == null ? 0 : rule.getScore();
            scores.add(score);
        }

        if (scores.size() > 0) {
            return Collections.max(scores);
        } else {
            return 0;
        }


    }
}
