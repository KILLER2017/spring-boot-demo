package top.alvinsite.demo.dao.performance;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.dto.auth.ManagerUserDTO;
import top.alvinsite.demo.model.entity.performance.Awarded;
import top.alvinsite.demo.model.params.PerformanceQuery;

import java.util.List;

/**
 * @author Alvin
 */
@Repository
public interface AwardedDao extends BaseMapper<Awarded> {

    /**
     * 获取获奖列表
     * @param performanceQuery
     * @return
     */
    List<Awarded> findAwarded(PerformanceQuery performanceQuery);
    ManagerUserDTO findAuthorsById(String id);
}
