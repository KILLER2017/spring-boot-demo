package top.alvinsite.demo.dao.performance;

import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.param.PerformanceQuery;

import java.util.List;

/**
 * @author Alvin
 */
public interface BasePerformanceMapper<T> {

    /**
     * 获取列表数据
     * @param query
     * @return
     */
    List<T> findAll(PerformanceQuery query);

    /**
     * 根据项目ID获取项目成员列表
     * @param id
     * @return
     */
    List<ManagerUserDTO> findMembersById(String id);
}
