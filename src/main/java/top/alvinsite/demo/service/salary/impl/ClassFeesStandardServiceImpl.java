package top.alvinsite.demo.service.salary.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.alvinsite.demo.dao.salary.ClassFeesStandardDao;
import top.alvinsite.demo.model.entity.salary.ClassFeesStandard;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.service.salary.ClassFeesStandardService;

import java.util.List;

/**
 * @author Alvin
 */
@Service
public class ClassFeesStandardServiceImpl extends ServiceImpl<ClassFeesStandardDao, ClassFeesStandard> implements ClassFeesStandardService {
    @Override
    public List<ClassFeesStandard> findAll(PerformanceQuery query) {
        return baseMapper.selectList(Wrappers.<ClassFeesStandard>lambdaQuery()
                .eq(query.getYear() != null, ClassFeesStandard::getYear, query.getYear()));
    }
}
