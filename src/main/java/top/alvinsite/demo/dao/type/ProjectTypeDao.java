package top.alvinsite.demo.dao.type;

import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.type.ProjectTypeDTO;

import java.util.List;

@Repository
public interface ProjectTypeDao {
    List<ProjectTypeDTO> findAll();
    ProjectTypeDTO findOne(String id);
}
