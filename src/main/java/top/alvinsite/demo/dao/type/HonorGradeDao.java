package top.alvinsite.demo.dao.type;

import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.type.HonorGradeDTO;

import java.util.List;

@Repository
public interface HonorGradeDao {
    List<HonorGradeDTO> findAll();
}
