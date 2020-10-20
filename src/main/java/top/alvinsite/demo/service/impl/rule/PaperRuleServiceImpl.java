package top.alvinsite.demo.service.impl.rule;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.rule.PaperRuleDao;
import top.alvinsite.demo.model.dto.rule.PaperRuleDTO;
import top.alvinsite.demo.model.entity.type.PaperType;
import top.alvinsite.demo.model.entity.performance.Paper;
import top.alvinsite.demo.model.entity.rule.PaperRule;
import top.alvinsite.demo.model.params.RuleQuery;
import top.alvinsite.demo.service.rule.PaperRuleService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static top.alvinsite.demo.utils.BeanUtils.updateProperties;

@Slf4j
@Service
public class PaperRuleServiceImpl extends ServiceImpl<PaperRuleDao, PaperRule> implements PaperRuleService {
    @Autowired
    private PaperRuleDao paperRuleDao;

    @Override
    public List<PaperRuleDTO> list(RuleQuery ruleQuery) {
        return paperRuleDao.findAll(ruleQuery);
    }

    @Override
    public void save(List<PaperRule> paperRules) {
        // 删除旧的规则
        if (paperRules != null && !paperRules.isEmpty()) {
            PaperRule firstRule =  paperRules.get(0);

            RuleQuery ruleQuery = new RuleQuery();
            updateProperties(firstRule, ruleQuery);
            paperRuleDao.delete(ruleQuery);
        }

        // 保存新的规则
        paperRuleDao.saveBatch(paperRules);
    }

    @Override
    public PaperRule findRule(Paper paper) {
        PaperRule rule = paperRuleDao.selectOne(Wrappers.<PaperRule>lambdaQuery()
                .eq(PaperRule::getYear, paper.getApprovalProjectYear())
                .eq(PaperRule::getDepartment, paper.getDepartment().getId())
                .eq(PaperRule::getType, "")
        );

        if (null == rule) {
            log.info("找不到对应的绩效规则， {}", paper);
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
    public float getScore(Paper paper) {
        // 读取计分规则
        List<Float> scores = new ArrayList<>();

        int year = paper.getApprovalProjectYear();
        String department = paper.getDepartment().getId();
        for (PaperType type : paper.getPublicationType()) {
            PaperRule rule = findRule(year, department, type.getId());
            float score = rule == null ? 0 : rule.getScore();
            scores.add(score);
        }

        return Collections.max(scores);
    }
}
