package top.alvinsite.demo.dao.auth;

import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.auth.ManagerDTO;
import top.alvinsite.demo.model.entity.auth.ManagerUser;
import top.alvinsite.demo.model.params.ManagerUserQuery;
import top.alvinsite.demo.model.params.PerformanceQuery;

import java.util.List;

@Repository
public interface ManagerDao {
    List<ManagerDTO> findAll(PerformanceQuery performanceQuery);
    void saveBatch(List<ManagerUser> managerUsers);
    void updateOperator(PerformanceQuery performanceQuery);
    void deleteManagerUser(Integer id);
    String findDepartmentById(Integer id);
    void setInactive(Integer id);
    void reset();

    List<ManagerDTO> findAllByDepartment(ManagerUserQuery managerUserQuery);
    ManagerDTO findOneByAccount(String account);
    String[] findUnitIdsByAccount(String account);
}
