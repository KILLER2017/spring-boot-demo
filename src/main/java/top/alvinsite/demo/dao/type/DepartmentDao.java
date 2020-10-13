package top.alvinsite.demo.dao.type;

import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.entity.Department;

import java.util.List;

@Repository
public interface DepartmentDao {
    List<Department> findAll();
    List<Department> findManageUnit(String account);
    Department findOne(String id);
}
