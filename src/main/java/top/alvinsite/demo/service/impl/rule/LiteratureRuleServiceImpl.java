package top.alvinsite.demo.service.impl.rule;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.rule.LiteratureRuleDao;
import top.alvinsite.demo.dao.rule.LiteratureRuleFundingSourceDao;
import top.alvinsite.demo.dao.rule.LiteratureRuleRevisedDao;
import top.alvinsite.demo.dao.rule.LiteratureRuleTopicWithDongguanDao;
import top.alvinsite.demo.model.dto.performance.ResearcherPerformance;
import top.alvinsite.demo.model.dto.rule.LiteratureRuleDTO;
import top.alvinsite.demo.model.entity.performance.Literature;
import top.alvinsite.demo.model.entity.rule.*;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.RuleQuery;
import top.alvinsite.demo.service.rule.LiteratureRuleService;

import java.util.List;

import static top.alvinsite.demo.utils.BeanUtils.updateProperties;

@Slf4j
@Service
public class LiteratureRuleServiceImpl extends ServiceImpl<LiteratureRuleDao, LiteratureRule> implements LiteratureRuleService {
    @Autowired
    private LiteratureRuleFundingSourceDao literatureRuleFundingSourceDao;

    @Autowired
    private LiteratureRuleRevisedDao literatureRuleRevisedDao;

    @Autowired
    private LiteratureRuleTopicWithDongguanDao literatureRuleTopicWithDongguanDao;

    @Override
    public List<LiteratureRuleDTO> list(RuleQuery ruleQuery) {
        return baseMapper.findAll(ruleQuery);
    }

    @Override
    public void save(List<LiteratureRuleDTO> literatureRuleDTOS) {
        // 删除旧的规则
        if (literatureRuleDTOS != null && !literatureRuleDTOS.isEmpty()) {
            LiteratureRuleDTO firstRule =  literatureRuleDTOS.get(0);

            RuleQuery ruleQuery = new RuleQuery();
            updateProperties(firstRule, ruleQuery);
            baseMapper.delete(ruleQuery);
        }

        // 保存新的规则
        baseMapper.saveBatch(literatureRuleDTOS);
    }

    @Override
    public LiteratureRule findRule(Literature literature) {
        LiteratureRule rule = baseMapper.selectOne(Wrappers.<LiteratureRule>lambdaQuery()
                .eq(LiteratureRule::getYear, literature.getApprovalProjectYear())
                .eq(LiteratureRule::getDepartment, literature.getDepartment().getId())
                .eq(LiteratureRule::getType, literature.getType())
                .eq(LiteratureRule::getPublisherLevel, literature.getPublisherLevel())
                .le(LiteratureRule::getMin, literature.getWordCount())
                .gt(LiteratureRule::getMax, literature.getWordCount())
        );

        if (null == rule) {
            log.info("找不到对应的绩效规则， {}", literature);
        }

        return rule;
    }

    @Override
    public float getScore(Literature literature) {
        float score = 0;
        score += getBasicScore(literature);
        score += getFundingSourceScore(literature);
        score += getTopicWithDongguanScore(literature);
        score *= getRevisedScore(literature);
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
        return revised == null ? 1 : revised.getScore();
    }

    public List<LiteratureRuleFundingSource> getFundingSourceRules(RuleQuery ruleQuery) {
        return literatureRuleFundingSourceDao.selectList(Wrappers.<LiteratureRuleFundingSource>lambdaQuery()
                .eq(LiteratureRuleFundingSource::getDepartment, ruleQuery.getDepartment())
                .eq(LiteratureRuleFundingSource::getYear, ruleQuery.getYear())
        );
    }

    public List<LiteratureRuleTopicWithDongguan> getTopicWithDongguanRules(RuleQuery ruleQuery) {
        return literatureRuleTopicWithDongguanDao.selectList(Wrappers.<LiteratureRuleTopicWithDongguan>lambdaQuery()
                .eq(LiteratureRuleTopicWithDongguan::getDepartment, ruleQuery.getDepartment())
                .eq(LiteratureRuleTopicWithDongguan::getYear, ruleQuery.getYear())
        );
    }

    public List<LiteratureRuleRevised> getRevisedRules(RuleQuery ruleQuery) {
        return literatureRuleRevisedDao.selectList(Wrappers.<LiteratureRuleRevised>lambdaQuery()
                .eq(LiteratureRuleRevised::getDepartment, ruleQuery.getDepartment())
                .eq(LiteratureRuleRevised::getYear, ruleQuery.getYear())
        );
    }

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
}
