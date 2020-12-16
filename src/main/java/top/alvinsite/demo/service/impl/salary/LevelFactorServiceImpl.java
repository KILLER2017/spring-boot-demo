package top.alvinsite.demo.service.impl.salary;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.salary.LevelFactorDao;
import top.alvinsite.demo.model.entity.salary.LevelFactor;
import top.alvinsite.demo.service.salary.LevelFactorService;

/**
 * @author Alvin
 */
@Service
public class LevelFactorServiceImpl extends ServiceImpl<LevelFactorDao, LevelFactor> implements LevelFactorService {
}
