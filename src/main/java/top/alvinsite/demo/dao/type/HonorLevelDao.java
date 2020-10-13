package top.alvinsite.demo.dao.type;

import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.type.HonorLevelDTO;

import java.util.List;

@Repository
public interface HonorLevelDao {
    List<HonorLevelDTO> findAll();
}
