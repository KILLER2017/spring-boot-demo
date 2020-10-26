package top.alvinsite.demo.dao.rule;

import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.entity.rule.SequencePoints;

import java.util.List;

/**
 * @author Alvin
 */
@Repository
public interface SequencePointsDao {
    void saveBatch(List<SequencePoints> sequencePoints);
    void delete(String ruleType, String ruleId);
}
