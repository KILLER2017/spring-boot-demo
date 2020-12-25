package top.alvinsite.demo.dao.performance;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.entity.performance.Patent;
import top.alvinsite.demo.model.params.PerformanceQuery;

import java.util.List;

/**
 * @author Alvin
 */
@Repository
public interface PatentDao extends BaseMapper<Patent> {
    /**
     * 获取专利列表
     * @param performanceQuery 过滤条件
     * @return 列表数据
     */
    List<Patent> findPatent(PerformanceQuery performanceQuery);

    /**
     * 根据ID获取作者
     * @param id 专利作者
     * @return 作者列表
     */
    List<ManagerUserDTO> findAuthorsById(String id);
}
