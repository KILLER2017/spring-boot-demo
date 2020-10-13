package top.alvinsite.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.entity.ProjectMember;

@Repository
public interface ProjectMemberDao extends BaseMapper<ProjectMember> {
}
