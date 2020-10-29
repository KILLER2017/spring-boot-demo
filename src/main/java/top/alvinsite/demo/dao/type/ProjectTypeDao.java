package top.alvinsite.demo.dao.type;

import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.entity.type.ProjectType;
import top.alvinsite.demo.model.params.RuleQuery;

import java.util.List;

@Repository
public interface ProjectTypeDao {
    List<ProjectType> findAll(RuleQuery ruleQuery);
    ProjectType findOne(String id);
}
