package top.alvinsite.demo.dao.type;

import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.type.PatentScopeDTO;

import java.util.List;

@Repository
public interface PatentScopeDao {
    List<PatentScopeDTO> findAll();
}
