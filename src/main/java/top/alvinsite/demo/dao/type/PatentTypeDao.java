package top.alvinsite.demo.dao.type;

import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.type.PatentTypeDTO;

import java.util.List;

@Repository
public interface PatentTypeDao {
    List<PatentTypeDTO> findAll();
}
