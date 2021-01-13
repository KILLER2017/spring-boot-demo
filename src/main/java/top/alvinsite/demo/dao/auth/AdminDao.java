package top.alvinsite.demo.dao.auth;

import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.auth.AdminDTO;
import top.alvinsite.demo.model.entity.auth.Admin;
import top.alvinsite.demo.model.param.PerformanceQuery;

import java.util.List;

@Repository
public interface AdminDao {
    public List<AdminDTO> findAll(PerformanceQuery performanceQuery);
    public void delete(Integer id);
    public void saveBatch(List<Admin> adminDTOS);
    AdminDTO findOneByAccount(String account);
}
