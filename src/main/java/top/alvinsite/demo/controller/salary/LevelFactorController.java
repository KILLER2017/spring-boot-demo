package top.alvinsite.demo.controller.salary;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.alvinsite.demo.model.entity.salary.LevelFactor;
import top.alvinsite.demo.model.params.LevelFactorParam;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.salary.LevelFactorUpdateParam;
import top.alvinsite.demo.model.validation.ValidationGroup2;
import top.alvinsite.demo.service.salary.LevelFactorService;

import java.util.List;
import java.util.stream.Collectors;

import static top.alvinsite.utils.BeanUtils.transformFrom;

/**
 * @author Administrator
 */
@Slf4j
@RestController
@RequestMapping("salary/level-factor")
public class LevelFactorController extends AbstractSalaryController<LevelFactorService, LevelFactor, LevelFactorUpdateParam> {

    @Override
    protected Class<LevelFactor> getEntityClass() {
        return LevelFactor.class;
    }

    @Override
    protected Class<LevelFactorUpdateParam> getParamClass() {
        return LevelFactorUpdateParam.class;
    }

    @Override
    protected String getOutputExcelName() {
        return "级差系数.xlsx";
    }

    @Override
    protected String getExcelTemplateName() {
        return "级差系数导入模板.xlsx";
    }

    @Override
    protected LevelFactor handle(PerformanceQuery query, LevelFactor entity) {
        entity.setYear(query.getYear());
        entity.setDepartment(query.getDepartmentId());
        LevelFactorParam param = transformFrom(entity, LevelFactorParam.class);
        LevelFactor oObject = baseService.getOne(param);

        if (oObject != null) {
            entity.setId(oObject.getId());
        }
        return entity;
    }

    @GetMapping("delete")
    public void delete(PerformanceQuery query) {
        baseService.remove(Wrappers.<LevelFactor>lambdaQuery()
                .eq(LevelFactor::getDepartment, query.getDepartmentId())
                .eq(LevelFactor::getYear, query.getYear())
        );
    }

    @GetMapping("type")
    public List<String> getTypes(@Validated(ValidationGroup2.class) PerformanceQuery query) {
        QueryWrapper<LevelFactor> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("distinct type")
                .eq(query.getYear() != null, "year", query.getYear())
                .eq(query.getDepartmentId() != null, "department", query.getDepartmentId());
        List<LevelFactor> list = baseService.list(queryWrapper);
        List<String> results;
        results = list.stream().distinct().map(LevelFactor::getType).collect(Collectors.toList());
        return results;
    }

    @GetMapping("level")
    public List<String> getLevels(@Validated(ValidationGroup2.class) PerformanceQuery query) {
        QueryWrapper<LevelFactor> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("distinct level")
                .eq(query.getYear() != null,"year", query.getYear())
                .eq(query.getDepartmentId() != null, "department", query.getDepartmentId());
        List<LevelFactor> list = baseService.list(queryWrapper);
        List<String> results;
        results = list.stream().distinct().map(LevelFactor::getLevel).collect(Collectors.toList());
        return results;
    }
}
