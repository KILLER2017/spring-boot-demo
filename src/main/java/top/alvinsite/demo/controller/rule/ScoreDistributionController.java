package top.alvinsite.demo.controller.rule;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.alvinsite.demo.model.entity.rule.ScoreDistribution;
import top.alvinsite.demo.service.ScoreDistributionService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Alvin
 */
@Slf4j
@RestController
@RequestMapping("performance/rule/score-distribution")
@Validated
public class ScoreDistributionController {

    @Autowired
    private ScoreDistributionService scoreDistributionService;

    @GetMapping
    public List<ScoreDistribution> get(@Valid @NotNull Integer year) {
        return scoreDistributionService.list(
                Wrappers.<ScoreDistribution>lambdaQuery()
                        .eq(year != null, ScoreDistribution::getYear, year)
                        .orderByAsc(ScoreDistribution::getTotals, ScoreDistribution::getPosition)
        );
    }

    @PostMapping
    public void post(@RequestBody List<ScoreDistribution> scoreDistributions) {
        scoreDistributions.stream().map(scoreDistribution -> {
            ScoreDistribution oldRow = scoreDistributionService.getOne(Wrappers.<ScoreDistribution>lambdaQuery()
                    .eq(ScoreDistribution::getYear, scoreDistribution.getYear())
                    .eq(ScoreDistribution::getTotals, scoreDistribution.getTotals())
                    .eq(ScoreDistribution::getPosition, scoreDistribution.getPosition())
            );

            scoreDistribution.setId(oldRow.getId());
            return scoreDistribution;
        });

        scoreDistributionService.saveOrUpdateBatch(scoreDistributions);
    }
}
