package top.alvinsite.demo.service.rule.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.rule.CopyrightRuleDao;
import top.alvinsite.demo.model.entity.performance.Copyright;
import top.alvinsite.demo.model.entity.rule.CopyrightRule;
import top.alvinsite.demo.model.params.RuleQuery;
import top.alvinsite.demo.service.rule.CopyrightRuleService;

import java.util.List;

@Slf4j
@Service
public class CopyrightRuleServiceImpl extends ServiceImpl<CopyrightRuleDao, CopyrightRule> implements CopyrightRuleService {
    @Autowired
    private CopyrightRuleDao copyrightRuleDao;

    @Override
    public List<CopyrightRule> findAll(RuleQuery ruleQuery) {
        return copyrightRuleDao.findAll(ruleQuery);
    }

    @Override
    public CopyrightRule findRule(Copyright project) {
        CopyrightRule rule = copyrightRuleDao.selectOne(Wrappers.<CopyrightRule>lambdaQuery()
                .eq(CopyrightRule::getYear, project.getApprovalProjectYear())
                .eq(CopyrightRule::getDepartment, project.getDepartment().getId())
                .eq(CopyrightRule::getType, project.getType())
        );

        if (null == rule) {
            log.info("找不到对应的绩效规则， {}", project);
        }

        return rule;
    }

    @Override
    public float getScore(Copyright project) {
        CopyrightRule rule = findRule(project);
        return rule == null ? 0 : rule.getScore();
    }
}
