package top.alvinsite.framework.springsecurity.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.framework.springsecurity.entity.User;

/**
 * @author Alvin
 */
@Repository
public interface UserDao extends BaseMapper<User> {
}
