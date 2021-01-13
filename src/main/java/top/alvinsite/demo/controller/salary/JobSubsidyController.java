package top.alvinsite.demo.controller.salary;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.model.entity.salary.JobSubsidy;
import top.alvinsite.demo.model.param.PerformanceQuery;
import top.alvinsite.demo.model.param.salary.JobSubsidyUpdateParam;
import top.alvinsite.demo.service.salary.JobSubsidyService;

import static top.alvinsite.util.BeanUtils.transformFrom;

/**
 * 岗位津贴控制器
 * @author Alvin
 */
@RestController
@RequestMapping("salary/job-subsidy")
public class JobSubsidyController extends BaseSalaryController<JobSubsidyService, JobSubsidy, JobSubsidyUpdateParam> {


    @Override
    protected Class<JobSubsidy> getEntityClass() {
        return JobSubsidy.class;
    }

    @Override
    protected Class<JobSubsidyUpdateParam> getParamClass() {
        return JobSubsidyUpdateParam.class;
    }

    @Override
    protected String getOutputExcelName() {
        return "岗位津贴.xlsx";
    }

    @Override
    protected String getExcelTemplateName() {
        return "岗位津贴导入模板.xlsx";
    }

    @Override
    protected JobSubsidy handle(PerformanceQuery query, JobSubsidy entity) {
        entity.setYear(query.getYear());

        // 查询是否已存在记录
        JobSubsidy oldRecord = baseService.getOne(Wrappers.<JobSubsidy>lambdaQuery()
                        .eq(JobSubsidy::getYear, query.getYear())
                        .eq(JobSubsidy::getAccount, entity.getAccount())
                , false);
        if (oldRecord != null) {
            entity.setId(oldRecord.getId());
        }

        // 计算并设置个人岗位津贴
        entity.setSubsidy(entity.getSubsidyFactor() * entity.getLength());
        return entity;
    }

    @Override
    public void update(@RequestBody JobSubsidyUpdateParam record) {
        JobSubsidy item = transformFrom(record, getEntityClass());
        assert item != null;
        String[] parameters = item.getId().split("#");
        item.setId(null);
        item.setYear(Integer.valueOf(parameters[0]));
        item.setAccount(parameters[1]);
        item.setSubsidy(item.getSubsidyFactor() * item.getLength());

        QueryWrapper<JobSubsidy> queryWrapper = new QueryWrapper<JobSubsidy>()
                .eq("account", item.getAccount())
                .eq("year", item.getYear());
        baseService.saveOrUpdate(item, queryWrapper);
    }

    @Override
    public void updateByPost(@RequestBody JobSubsidyUpdateParam record) {
        update(record);
    }
}
