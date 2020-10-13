package top.alvinsite.demo.dao.rule;

import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.entity.rule.SequencePoints;

import java.util.List;

@Repository
public interface SequencePointsDao {
    public void saveBatch(List<SequencePoints> sequencePoints);
    void delete(String ruleType, String ruleId);
}
