package top.alvinsite.demo.service.salary;

import top.alvinsite.demo.model.entity.salary.LevelFactor;
import top.alvinsite.demo.model.param.LevelFactorParam;

/**
 * @author Alvin
 */
public interface LevelFactorService extends SalaryService<LevelFactor> {

    /**
     * 获取指定一个级差系数
     * @param param 查询参数
     * @return 级差系数
     */
    LevelFactor getOne(LevelFactorParam param);
}
