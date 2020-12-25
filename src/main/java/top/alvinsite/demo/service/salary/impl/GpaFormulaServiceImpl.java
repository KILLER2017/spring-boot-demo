package top.alvinsite.demo.service.salary.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.alvinsite.demo.dao.salary.GpaFormulaDao;
import top.alvinsite.demo.model.entity.salary.GpaFormula;
import top.alvinsite.demo.model.params.SalaryRuleQuery;
import top.alvinsite.demo.service.salary.GpaFormulaService;

import java.util.Arrays;
import java.util.List;

/**
 * @author Administrator
 */
@Slf4j
@Service
public class GpaFormulaServiceImpl extends ServiceImpl<GpaFormulaDao, GpaFormula> implements GpaFormulaService {

    @Override
    public List<GpaFormula> findAll(SalaryRuleQuery salaryRuleQuery) {
        return baseMapper.findAll(salaryRuleQuery);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveBatch(String deleteIds, List<GpaFormula> gpaFormulas) {
        if (deleteIds != null) {
            String[] ids = deleteIds.split(",");
            log.info(Arrays.toString(ids));
        }

        baseMapper.saveBatch(gpaFormulas);
    }

}
