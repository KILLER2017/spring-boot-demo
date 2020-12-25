package top.alvinsite.demo.service.salary.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.salary.LevelFactorDao;
import top.alvinsite.demo.model.entity.salary.LevelFactor;
import top.alvinsite.demo.model.params.LevelFactorParam;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.service.salary.LevelFactorService;

import java.util.List;

/**
 * @author Alvin
 */
@Service
public class LevelFactorServiceImpl extends ServiceImpl<LevelFactorDao, LevelFactor> implements LevelFactorService {
    @Override
    public LevelFactor getOne(LevelFactorParam param) {
        return baseMapper.findOneByTypeAndLevel(param);
    }

    @Override
    public List<LevelFactor> findAll(PerformanceQuery query) {
        return baseMapper.findAll(query);
    }
}
