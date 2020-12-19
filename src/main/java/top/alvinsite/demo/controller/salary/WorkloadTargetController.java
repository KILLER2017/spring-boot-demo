package top.alvinsite.demo.controller.salary;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.alvinsite.demo.dao.salary.WorkloadTargetDao;
import top.alvinsite.demo.model.entity.salary.WorkloadTarget;
import top.alvinsite.demo.model.params.Page;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.WorkloadTargetParam;
import top.alvinsite.demo.model.params.salary.WorkloadTargetUpdateParam;
import top.alvinsite.demo.service.salary.WorkloadTargetService;
import top.alvinsite.utils.ExcelUtils;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static top.alvinsite.utils.BeanUtils.transformFrom;

/**
 * @author Administrator
 */
@Slf4j
@RestController
@RequestMapping("salary/workload-target")
public class WorkloadTargetController {
    @Autowired
    private WorkloadTargetDao workloadTargetDao;

    @Autowired
    private WorkloadTargetService workloadTargetService;

    @GetMapping
    public PageInfo<WorkloadTarget> getPageData(@Valid PerformanceQuery query, Page page) {
        PageHelper.startPage(page);
        return new PageInfo<>(workloadTargetDao.findAll(query));
    }

    @PostMapping("importExcel")
    public void importExcel(PerformanceQuery query, @RequestParam(value = "uploadFile") MultipartFile file) {
        List<WorkloadTarget> list = ExcelUtils.readExcel("", WorkloadTarget.class, file);

        list.forEach(
            item ->{
                item.setYear(query.getYear());
                item.setDepartment(query.getDepartment());
                WorkloadTargetParam param = transformFrom(item, WorkloadTargetParam.class);
                WorkloadTarget oObject = workloadTargetService.getOne(param);
                if (oObject != null) {
                    item.setId(oObject.getId());
                    workloadTargetService.updateById(item);
                } else {
                    workloadTargetService.save(item);
                }
            }
        );
    }

    /**
     * 获取数据导入Excel模板
     * @param response 请求响应
     */
    @PostMapping("template")
    public void getTemplate(HttpServletResponse response) {
        Workbook workbook = new ExcelUtils.Builder()
                .addSheet("sheet1", new ArrayList<>(), WorkloadTarget.class)
                .build();
        ExcelUtils.buildExcelDocument("目标工作量导入模板.xlsx", workbook, response);
    }

    /**
     * 目标工作量更新接口
     * @param param 目标工作量
     */
    @PutMapping
    public void update(@Valid @RequestBody WorkloadTargetUpdateParam param) {
        WorkloadTarget record = transformFrom(param, WorkloadTarget.class);
        assert record != null;
        workloadTargetService.updateById(record);
    }
}
