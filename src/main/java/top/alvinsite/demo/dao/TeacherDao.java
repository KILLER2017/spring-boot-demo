package top.alvinsite.demo.dao;

import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.AdminCandidateDTO;
import top.alvinsite.demo.model.dto.ManagerUserCandidateDTO;
import top.alvinsite.demo.model.entity.Teacher;

import java.util.List;

@Repository
public interface TeacherDao {
    List<Teacher> findAll();
    Teacher findOne(String account);
    List<AdminCandidateDTO> findAdminCandidate(String searchKey);
    List<ManagerUserCandidateDTO> findManagerUserCandidate(String searchKey);
}
