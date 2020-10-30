package top.alvinsite.demo.service.impl.salary;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.alvinsite.demo.dao.salary.RuleDao;
import top.alvinsite.demo.model.entity.salary.Rule;
import top.alvinsite.demo.service.salary.RuleService;

import java.util.List;

/**
 * @author Administrator
 */
@Slf4j
@Service
public class RuleServiceImpl extends ServiceImpl<RuleDao, Rule> implements RuleService {

    @Override
    public List<Rule> list(Integer year) {
        return baseMapper.findAll(year);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveBatch(String deleteIds, List<Rule> rules) {
        if (deleteIds != null) {
            String[] ids = deleteIds.split(",");
            log.info(String.valueOf(ids));
            // baseMapper.deleteByIds(ids);
        }

        baseMapper.saveBatch(rules);
    }

}
