package top.alvinsite.demo.controller.salary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.model.entity.salary.WorkloadTarget;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.WorkloadTargetParam;
import top.alvinsite.demo.model.params.salary.WorkloadTargetUpdateParam;
import top.alvinsite.demo.service.salary.WorkloadTargetService;

import static top.alvinsite.utils.BeanUtils.transformFrom;

/**
 * @author Administrator
 */
@Slf4j
@RestController
@RequestMapping("salary/workload-target")
public class WorkloadTargetController extends AbstractSalaryController<WorkloadTargetService, WorkloadTarget, WorkloadTargetUpdateParam> {


    @Override
    protected Class<WorkloadTarget> getEntityClass() {
        return WorkloadTarget.class;
    }

    @Override
    protected Class<WorkloadTargetUpdateParam> getParamClass() {
        return WorkloadTargetUpdateParam.class;
    }

    @Override
    protected String getOutputExcelName() {
        return "目标工作量.xlsx";
    }

    @Override
    protected String getExcelTemplateName() {
        return "目标工作量导入模板.xlsx";
    }

    @Override
    protected WorkloadTarget handle(PerformanceQuery query, WorkloadTarget entity) {
        entity.setYear(query.getYear());
        entity.setDepartment(query.getDepartmentId());
        WorkloadTargetParam param = transformFrom(entity, WorkloadTargetParam.class);
        WorkloadTarget oObject = baseService.getOne(param);
        if (oObject != null) {
            entity.setId(oObject.getId());
        }
        return entity;
    }



}
