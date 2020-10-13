package top.alvinsite.demo.dao.type;

import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.type.ProjectLevelDTO;

import java.util.List;

@Repository
public interface ProjectLevelDao {
    List<ProjectLevelDTO> findAll();
    ProjectLevelDTO findOne(String id);
}
