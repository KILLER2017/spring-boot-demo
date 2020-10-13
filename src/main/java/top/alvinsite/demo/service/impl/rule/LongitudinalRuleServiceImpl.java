package top.alvinsite.demo.service.impl.rule;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.rule.LongitudinalRuleDao;
import top.alvinsite.demo.model.dto.rule.LongitudinalRuleDTO;
import top.alvinsite.demo.model.entity.performance.LongitudinalProject;
import top.alvinsite.demo.model.entity.rule.LongitudinalProjectRule;
import top.alvinsite.demo.model.params.RuleQuery;
import top.alvinsite.demo.service.rule.LongitudinalRuleService;

import java.util.List;

import static top.alvinsite.demo.utils.BeanUtils.updateProperties;

@Slf4j
@Service
public class LongitudinalRuleServiceImpl implements LongitudinalRuleService {
    @Autowired
    private LongitudinalRuleDao longitudinalRuleDao;

    @Override
    public List<LongitudinalRuleDTO> list(RuleQuery ruleQuery) {
        return longitudinalRuleDao.findAll(ruleQuery);
    }

    @Override
    public void save(List<LongitudinalProjectRule> longitudinalProjectRules) {
        // 删除旧的规则
        if (longitudinalProjectRules != null && !longitudinalProjectRules.isEmpty()) {
            LongitudinalProjectRule firstRule =  longitudinalProjectRules.get(0);

            RuleQuery ruleQuery = new RuleQuery();
            updateProperties(firstRule, ruleQuery);
            longitudinalRuleDao.delete(ruleQuery);
        }

        // 保存新的规则
        longitudinalRuleDao.saveBatch(longitudinalProjectRules);
    }

    @Override
    public LongitudinalProjectRule findOneByLongitudinalProject(LongitudinalProject longitudinalProject) {
        LongitudinalProjectRule rule = longitudinalRuleDao.selectOne(Wrappers.<LongitudinalProjectRule>lambdaQuery()
                .eq(LongitudinalProjectRule::getType, longitudinalProject.getTypeId())
                .eq(LongitudinalProjectRule::getLevel, longitudinalProject.getLevelId()
                ));

        if (null == rule) {
            log.info("找不到对应的绩效规则， {}", longitudinalProject);
        }

        return rule;
    }
}
