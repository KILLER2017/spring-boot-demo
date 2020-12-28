package top.alvinsite.demo.service.rule.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.rule.LiteratureRuleDao;
import top.alvinsite.demo.dao.rule.LiteratureRuleFundingSourceDao;
import top.alvinsite.demo.dao.rule.LiteratureRuleRevisedDao;
import top.alvinsite.demo.dao.rule.LiteratureRuleTopicWithDongguanDao;
import top.alvinsite.demo.model.entity.performance.Literature;
import top.alvinsite.demo.model.entity.rule.LiteratureRule;
import top.alvinsite.demo.model.entity.rule.LiteratureRuleFundingSource;
import top.alvinsite.demo.model.entity.rule.LiteratureRuleRevised;
import top.alvinsite.demo.model.entity.rule.LiteratureRuleTopicWithDongguan;
import top.alvinsite.demo.model.params.RuleQuery;
import top.alvinsite.demo.service.rule.LiteratureRuleService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Administrator
 */
@Slf4j
@Service
public class LiteratureRuleServiceImpl extends AbstractRuleService<LiteratureRuleDao, Literature, LiteratureRule> implements LiteratureRuleService {
    @Autowired
    private LiteratureRuleFundingSourceDao literatureRuleFundingSourceDao;

    @Autowired
    private LiteratureRuleRevisedDao literatureRuleRevisedDao;

    @Autowired
    private LiteratureRuleTopicWithDongguanDao literatureRuleTopicWithDongguanDao;

    @Override
    public List<LiteratureRule> findAll(RuleQuery ruleQuery) {
        return baseMapper.findAll(ruleQuery);
    }

    @Override
    public LiteratureRule findRule(Literature project) {
        LiteratureRule rule = baseMapper.selectOne(Wrappers.<LiteratureRule>lambdaQuery()
                .eq(LiteratureRule::getYear, project.getApprovalProjectYear())
                .eq(LiteratureRule::getDepartment, project.getDepartment().getId())
                .eq(LiteratureRule::getType, project.getType())
                .eq(LiteratureRule::getPublisherLevel, project.getPublisherLevel())
                .le(LiteratureRule::getMin, project.getWordCount())
                .gt(LiteratureRule::getMax, project.getWordCount())
        );

        if (null == rule) {
            log.info("找不到对应的绩效规则， {}", project);
        }

        return rule;
    }

    @Override
    public float getScore(Literature project) {
        float score = 0;
        score += getBasicScore(project);
        score += getFundingSourceScore(project);
        score += getTopicWithDongguanScore(project);
        score *= getRevisedScore(project);
        return score;
    }

    private float getBasicScore(Literature literature) {
        LiteratureRule rule = findRule(literature);
        return rule == null ? 0 : rule.getScore();
    }

    private float getFundingSourceScore(Literature literature) {
        LiteratureRuleFundingSource fundingSource = literatureRuleFundingSourceDao.selectOne(Wrappers.<LiteratureRuleFundingSource>lambdaQuery()
                .eq(LiteratureRuleFundingSource::getYear, literature.getApprovalProjectYear())
                .eq(LiteratureRuleFundingSource::getDepartment, literature.getDepartment().getId())
                .eq(LiteratureRuleFundingSource::getType, literature.getFundingSource())
        );

        return fundingSource == null ? 0 : fundingSource.getScore();
    }

    private float getTopicWithDongguanScore(Literature literature) {
        LiteratureRuleTopicWithDongguan topicWithDongguan = literatureRuleTopicWithDongguanDao.selectOne(Wrappers.<LiteratureRuleTopicWithDongguan>lambdaQuery()
                .eq(LiteratureRuleTopicWithDongguan::getYear, literature.getApprovalProjectYear())
                .eq(LiteratureRuleTopicWithDongguan::getDepartment, literature.getDepartment().getId())
                .eq(LiteratureRuleTopicWithDongguan::isTopicWithDongguan, literature.isTopicWithDongguan()));
        return topicWithDongguan == null ? 0 : topicWithDongguan.getScore();
    }

    private float getRevisedScore(Literature literature) {
        LiteratureRuleRevised revised = literatureRuleRevisedDao.selectOne(Wrappers.<LiteratureRuleRevised>lambdaQuery()
                .eq(LiteratureRuleRevised::getYear, literature.getApprovalProjectYear())
                .eq(LiteratureRuleRevised::getDepartment, literature.getDepartment().getId())
                .eq(LiteratureRuleRevised::isRevised, literature.isRevised()));
        return (revised == null ? 100 : revised.getScore()) / 100;
    }

    @Override
    public List<LiteratureRuleFundingSource> getFundingSourceRules(RuleQuery ruleQuery) {
        return literatureRuleFundingSourceDao.selectList(Wrappers.<LiteratureRuleFundingSource>lambdaQuery()
                .eq(LiteratureRuleFundingSource::getDepartment, ruleQuery.getDepartment())
                .eq(LiteratureRuleFundingSource::getYear, ruleQuery.getYear())
                .orderByAsc(LiteratureRuleFundingSource::getId)
        );
    }

    @Override
    public List<LiteratureRuleTopicWithDongguan> getTopicWithDongguanRules(RuleQuery ruleQuery) {
        return literatureRuleTopicWithDongguanDao.selectList(Wrappers.<LiteratureRuleTopicWithDongguan>lambdaQuery()
                .eq(LiteratureRuleTopicWithDongguan::getDepartment, ruleQuery.getDepartment())
                .eq(LiteratureRuleTopicWithDongguan::getYear, ruleQuery.getYear())
                .orderByAsc(LiteratureRuleTopicWithDongguan::getId)
        );
    }

    @Override
    public List<LiteratureRuleRevised> getRevisedRules(RuleQuery ruleQuery) {
        return literatureRuleRevisedDao.selectList(Wrappers.<LiteratureRuleRevised>lambdaQuery()
                .eq(LiteratureRuleRevised::getDepartment, ruleQuery.getDepartment())
                .eq(LiteratureRuleRevised::getYear, ruleQuery.getYear())
                .orderByAsc(LiteratureRuleRevised::getId)
        );
    }

    @Override
    public void saveFundingSourceRules(List<LiteratureRuleFundingSource> rules) {
        for (LiteratureRuleFundingSource rule: rules) {
            // 筛选条件
            LambdaQueryWrapper<LiteratureRuleFundingSource> lambdaQueryWrapper = Wrappers.<LiteratureRuleFundingSource>lambdaQuery()
                    .eq(LiteratureRuleFundingSource::getDepartment, rule.getDepartment())
                    .eq(LiteratureRuleFundingSource::getYear, rule.getYear())
                    .eq(LiteratureRuleFundingSource::getType, rule.getType());

            LiteratureRuleFundingSource ruleRevised = literatureRuleFundingSourceDao.selectOne(lambdaQueryWrapper);
            if (ruleRevised == null) {
                literatureRuleFundingSourceDao.insert(rule);
            } else {
                literatureRuleFundingSourceDao.update(rule, lambdaQueryWrapper);
            }
        }
    }

    @Override
    public void saveTopicWithDongguanRules(List<LiteratureRuleTopicWithDongguan> rules) {
        for (LiteratureRuleTopicWithDongguan rule: rules) {
            // 筛选条件
            LambdaQueryWrapper<LiteratureRuleTopicWithDongguan> lambdaQueryWrapper = Wrappers.<LiteratureRuleTopicWithDongguan>lambdaQuery()
                    .eq(LiteratureRuleTopicWithDongguan::getDepartment, rule.getDepartment())
                    .eq(LiteratureRuleTopicWithDongguan::getYear, rule.getYear())
                    .eq(LiteratureRuleTopicWithDongguan::isTopicWithDongguan, rule.isTopicWithDongguan());

            LiteratureRuleTopicWithDongguan ruleRevised = literatureRuleTopicWithDongguanDao.selectOne(lambdaQueryWrapper);
            if (ruleRevised == null) {
                literatureRuleTopicWithDongguanDao.insert(rule);
            } else {
                literatureRuleTopicWithDongguanDao.update(rule, lambdaQueryWrapper);
            }
        }
    }

    @Override
    public void saveRevisedRules(List<LiteratureRuleRevised> rules) {
        for (LiteratureRuleRevised rule: rules) {
            // 筛选条件
            LambdaQueryWrapper<LiteratureRuleRevised> lambdaQueryWrapper = Wrappers.<LiteratureRuleRevised>lambdaQuery()
                    .eq(LiteratureRuleRevised::getDepartment, rule.getDepartment())
                    .eq(LiteratureRuleRevised::getYear, rule.getYear())
                    .eq(LiteratureRuleRevised::isRevised, rule.isRevised());

            LiteratureRuleRevised ruleRevised = literatureRuleRevisedDao.selectOne(lambdaQueryWrapper);
            if (ruleRevised == null) {
                literatureRuleRevisedDao.insert(rule);
            } else {
                literatureRuleRevisedDao.update(rule, lambdaQueryWrapper);
            }
        }
    }

    @Override
    public void copyRule(String sourceDepartment, int sourceYear, String targetDepartment, int targetYear) {
        // 复制主表数据
        super.copyRule(sourceDepartment, sourceYear, targetDepartment, targetYear);
        // 复制literature_rule_funding_source
        copyFundingSourceRule(sourceDepartment, sourceYear, targetDepartment, targetYear);
        // 复制literature_rule_revised
        copyRevisedRule(sourceDepartment, sourceYear, targetDepartment, targetYear);
        // 复制literature_rule_topic_with_dongguan
        copyTopicWithDongguanRule(sourceDepartment, sourceYear, targetDepartment, targetYear);
    }

    private void copyFundingSourceRule(String sourceDepartment, int sourceYear, String targetDepartment, int targetYear) {
        // 读取源规则
        RuleQuery query = new RuleQuery();
        query.setDepartment(sourceDepartment);
        query.setYear(sourceYear);
        List<LiteratureRuleFundingSource> rules = getFundingSourceRules(query);

        List<LiteratureRuleFundingSource> targetRules = rules.stream().map(item -> {
            item.setDepartment(targetDepartment);
            item.setYear(targetYear);
            return item;
        }).collect(Collectors.toList());

        saveFundingSourceRules(targetRules);
    }

    private void copyRevisedRule(String sourceDepartment, int sourceYear, String targetDepartment, int targetYear) {
        // 读取源规则
        RuleQuery query = new RuleQuery();
        query.setDepartment(sourceDepartment);
        query.setYear(sourceYear);
        List<LiteratureRuleRevised> rules = getRevisedRules(query);

        List<LiteratureRuleRevised> targetRules = rules.stream().map(item -> {
            item.setDepartment(targetDepartment);
            item.setYear(targetYear);
            return item;
        }).collect(Collectors.toList());

        saveRevisedRules(targetRules);
    }

    private void copyTopicWithDongguanRule(String sourceDepartment, int sourceYear, String targetDepartment, int targetYear) {
        // 读取源规则
        RuleQuery query = new RuleQuery();
        query.setDepartment(sourceDepartment);
        query.setYear(sourceYear);
        List<LiteratureRuleTopicWithDongguan> rules = getTopicWithDongguanRules(query);

        List<LiteratureRuleTopicWithDongguan> targetRules = rules.stream().map(item -> {
            item.setDepartment(targetDepartment);
            item.setYear(targetYear);
            return item;
        }).collect(Collectors.toList());

        saveTopicWithDongguanRules(targetRules);
    }
}
