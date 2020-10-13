package top.alvinsite.demo.dao.type;

import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.type.BookTypeDTO;

import java.util.List;

@Repository
public interface BookTypeDao {
    List<BookTypeDTO> findAll();
    BookTypeDTO findOne(String id);
}
