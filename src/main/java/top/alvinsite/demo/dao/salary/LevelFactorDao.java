package top.alvinsite.demo.dao.salary;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.alvinsite.demo.model.entity.salary.LevelFactor;
import top.alvinsite.demo.model.params.LevelFactorParam;

import java.util.List;

@Repository
public interface LevelFactorDao extends BaseMapper<LevelFactor> {

    List<LevelFactor> findAll();

    LevelFactor findOne(Integer id);


    LevelFactor findOneByTypeAndLevel(LevelFactorParam levelFactorParam);
    void save(LevelFactor levelFactor);
}
