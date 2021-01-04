package top.alvinsite.demo.controller.salary;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("delete")
    public void delete(PerformanceQuery query) {
        baseService.remove(Wrappers.<WorkloadTarget>lambdaQuery()
                .eq(WorkloadTarget::getDepartment, query.getDepartmentId())
                .eq(WorkloadTarget::getYear, query.getYear())
        );
    }

}
