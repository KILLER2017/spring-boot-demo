package top.alvinsite.demo.controller.salary;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.model.entity.salary.JobSubsidy;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.salary.JobSubsidyUpdateParam;
import top.alvinsite.demo.service.salary.JobSubsidyService;

import static top.alvinsite.utils.BeanUtils.transformFrom;

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
    public void update(@RequestBody JobSubsidyUpdateParam param) {
        JobSubsidy jobSubsidy = transformFrom(param, JobSubsidy.class);
        assert jobSubsidy != null;
        jobSubsidy.setSubsidy(jobSubsidy.getSubsidyFactor() * jobSubsidy.getLength());
        baseService.updateById(jobSubsidy);
    }

    @Override
    public void updateByPost(@RequestBody JobSubsidyUpdateParam record) {
        update(record);
    }
}
