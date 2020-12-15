package top.alvinsite.demo.controller.salary;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.alvinsite.demo.dao.salary.WorkloadTargetDao;
import top.alvinsite.demo.model.entity.salary.WorkloadTarget;
import top.alvinsite.demo.model.params.Page;
import top.alvinsite.demo.model.params.SalaryQuery;
import top.alvinsite.demo.model.params.WorkloadTargetParam;
import top.alvinsite.framework.springsecurity.entity.User;
import top.alvinsite.utils.ExcelUtils;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * @author Administrator
 */
@Slf4j
@RestController
@RequestMapping("salary/workload-target")
public class WorkloadTargetController {
    @Autowired
    private WorkloadTargetDao workloadTargetDao;

    @GetMapping
    public PageInfo<WorkloadTarget> list(@Valid SalaryQuery salaryQuery, Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        return new PageInfo<>(workloadTargetDao.findAll(salaryQuery));
    }

    @PostMapping
    public void save(@RequestBody WorkloadTarget workloadTarget) {
        workloadTargetDao.save(workloadTarget);
    }

    @PostMapping("importExcel/{department}")
    public void importExcel(@PathVariable String department, @RequestParam(value = "uploadFile") MultipartFile file) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if ("manager".equals(user.getUserGroup()) && !Arrays.asList(user.getManageUnits()).contains(department)) {
            throw new IllegalArgumentException("部门参数错误，只能向您管理的部门导入数据");
        }

        List<WorkloadTarget> list = ExcelUtils.readExcel("", WorkloadTarget.class, file);

        list.forEach(
            item ->{
                item.setDepartment(department);
                WorkloadTarget oObject = workloadTargetDao.findOneByLevelAndPostType(new WorkloadTargetParam(department, item.getLevel(), item.getType()));
                if (oObject != null) {
                    item.setId(oObject.getId());
                }

                workloadTargetDao.save(item);
            }

        );
    }

    @PostMapping("outputExcel")
    public void outputExcel() {

    }
}
