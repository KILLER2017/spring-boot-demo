package top.alvinsite.demo.service.impl.salary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.alvinsite.demo.dao.salary.RuleDao;
import top.alvinsite.demo.model.entity.salary.Rule;
import top.alvinsite.demo.service.salary.RuleService;

import java.util.List;

@Slf4j
@Service
public class RuleServiceImpl implements RuleService {

    @Autowired
    private RuleDao ruleDao;


    @Override
    public List<Rule> list(Integer year) {
        return ruleDao.findAll(year);
    }

    @Transactional
    @Override
    public void saveBatch(String deleteIds, List<Rule> rules) {
        if (deleteIds != null) {
            String[] ids = deleteIds.split(",");
            log.info(String.valueOf(ids));
            ruleDao.deleteByIds(ids);
        }

        ruleDao.saveBatch(rules);
    }

    @Override
    public void delete(String[] ids) {
        ruleDao.deleteByIds(ids);
    }
}
