package top.alvinsite.demo.dao.salary;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.entity.salary.LevelFactor;
import top.alvinsite.demo.model.params.LevelFactorParam;

import java.util.List;

@Repository
public interface LevelFactorDao extends BaseMapper<LevelFactor> {
    @Select("        select\n" +
            "            id,\n" +
            "            type,\n" +
            "            level,\n" +
            "            factor\n" +
            "        from level_factor")
    List<LevelFactor> findAll();

    @Select("        select\n" +
            "            id,\n" +
            "            type,\n" +
            "            level,\n" +
            "            factor\n" +
            "        from level_factor\n" +
            "        where id = #{id}")
    LevelFactor findOne(Integer id);


    LevelFactor findOneByTypeAndLevel(LevelFactorParam levelFactorParam);
    void save(LevelFactor levelFactor);
}
