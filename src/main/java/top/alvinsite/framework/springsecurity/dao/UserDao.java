package top.alvinsite.framework.springsecurity.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.entity.Department;
import top.alvinsite.framework.springsecurity.entity.User;

/**
 * @author Alvin
 */
@Repository
public interface UserDao extends BaseMapper<User> {

    /**
     * 根据ID查询单位
     * @param id 单位ID
     * @return 单位
     */
    @Select("SELECT * FROM department WHERE id = #{id}")
    Department findOneById(String id);

    /**
     * 根据用户名获取用户信息
     * @param username 用户名
     * @return 用户信息
     */
    @Results(id = "userMap", value = {
            @Result(property = "id", column = "gh"),
            @Result(property = "username", column = "gh"),
            @Result(property = "nickname", column = "xm"),
            @Result(property = "department", column = "dwh", one = @One(select = "top.alvinsite.framework.springsecurity.dao.UserDao.findOneById")),
    })
    @Select("SELECT * FROM gxjg0101 WHERE gh = #{username}")
    User findOneByUsername(String username);
}
