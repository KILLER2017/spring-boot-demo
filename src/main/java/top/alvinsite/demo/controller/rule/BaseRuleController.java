package top.alvinsite.demo.controller.rule;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.EnumOrdinalTypeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import top.alvinsite.demo.model.entity.rule.BaseRuleEntity;
import top.alvinsite.demo.model.entity.rule.ScoreDistributionConfig;
import top.alvinsite.demo.model.params.RuleQuery;
import top.alvinsite.demo.model.support.UserInfo;
import top.alvinsite.demo.model.vo.RuleVO;
import top.alvinsite.demo.service.rule.IRuleService;
import top.alvinsite.demo.service.rule.ScoreDistributionConfigService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static top.alvinsite.demo.utils.BeanUtils.updateProperties;

@Slf4j
@Validated
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseRuleController<M extends IRuleService<T>, T extends BaseRuleEntity> {

    @Setter
    private String performance;

    @Autowired
    protected M baseService;

    @Autowired
    protected ScoreDistributionConfigService scoreDistributionConfigService;

    protected void addManagerLimit(UserInfo userInfo, RuleQuery ruleQuery) {
        // 如果用户不是系统管理员，则限定只能查询自己管理机构的数据
        if (userInfo.getUserGroup() != "admin" && userInfo.getManageUnitId() != null) {
            ruleQuery.setDepartment(userInfo.getManageUnitId());
        }
    }


    @GetMapping
    public RuleVO get(@RequestHeader("authorization") UserInfo userInfo, @Valid RuleQuery ruleQuery) {
        log.info("request base controller");
        // 如果用户不是系统管理员，则限定只能查询自己管理机构的数据
        addManagerLimit(userInfo, ruleQuery);

        QueryWrapper<T> query = new QueryWrapper<>();
        query.eq(ruleQuery.getDepartment() != null, "department", ruleQuery.getDepartment())
                .eq(ruleQuery.getYear() != null, "year", ruleQuery.getYear());

        RuleVO ruleVO = new RuleVO();

        ruleVO.setUseScoreDistribution(scoreDistributionConfigService.useScoreDistribute(ruleQuery.getYear(), ruleQuery.getDepartment(), performance));
        ruleVO.setList(baseService.list(query));
        return ruleVO;
    };

    @PostMapping
    @Transactional
    public void post(@RequestHeader("authorization") UserInfo userInfo, @Valid RuleQuery ruleQuery, boolean useScoreDistribution, @RequestBody @Valid List<T> rules) {
        // 如果用户不是系统管理员，则限定只能保存自己管理机构的数据
        addManagerLimit(userInfo, ruleQuery);

        rules.stream().map((rule -> {
            rule.setDepartment(ruleQuery.getDepartment());
            rule.setYear(ruleQuery.getYear());
            return rule;
        })).collect(Collectors.toList());

        Map<String, Object> map = new HashMap<>();

        map.put("department", ruleQuery.getDepartment());
        map.put("year", ruleQuery.getYear());


        ScoreDistributionConfig config = new ScoreDistributionConfig();
        updateProperties(ruleQuery, config);
        config.setPerformance(performance);
        config.setActive(useScoreDistribution);

        scoreDistributionConfigService.applyConfig(config);
        baseService.removeByMap(map);
        baseService.saveOrUpdateBatch(rules);
    };

}
