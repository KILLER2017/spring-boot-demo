package top.alvinsite.demo.dao.type;

import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.type.PaperTypeDTO;

import java.util.List;

@Repository
public interface PaperTypeDao {
    List<PaperTypeDTO> findAll();
    PaperTypeDTO findOne(String id);
}
