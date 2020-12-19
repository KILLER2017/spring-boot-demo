package top.alvinsite.demo.controller.salary;

import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.alvinsite.demo.model.entity.salary.IncentiveWage;
import top.alvinsite.demo.model.params.Page;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.salary.JobSubsidyUpdateParam;
import top.alvinsite.demo.service.salary.IncentiveWageService;
import top.alvinsite.demo.service.salary.SalarySummaryService;
import top.alvinsite.utils.ExcelUtils;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;

import static top.alvinsite.utils.BeanUtils.transformFrom;

/**
 * 超课时津贴控制器
 * @author Alvin
 */
@RestController
@RequestMapping("salary/incentiveWage")
public class IncentiveWageController {

    @Autowired
    private SalarySummaryService salarySummaryService;

    @Autowired
    private IncentiveWageService incentiveWageService;

    @GetMapping
    public PageInfo<IncentiveWage> getPageData(PerformanceQuery query, Page page) {
        salarySummaryService.findAll(query);
        return null;
    }

    @PostMapping("importExcel")
    public void importExcel(PerformanceQuery query, MultipartFile file) {

    }

    @PostMapping("exportExcel")
    public void exportExcel() {

    }

    /**
     * 获取数据导入Excel模板
     * @param response 请求响应
     */

    @PostMapping("template")
    public void getTemplate(HttpServletResponse response) {
        Workbook workbook = new ExcelUtils.Builder()
                .addSheet("sheet1", new ArrayList<>(), IncentiveWage.class)
                .build();
        ExcelUtils.buildExcelDocument("激励绩效工资导入模板.xlsx", workbook, response);
    }

    /**
     * 岗位津贴更新接口
     * @param param 岗位津贴
     */
    @PutMapping
    public void update(@Valid @RequestBody JobSubsidyUpdateParam param) {
        IncentiveWage incentiveWage = transformFrom(param, IncentiveWage.class);
        assert incentiveWage != null;
        double factor = 1;
        incentiveWage.setIncentivePerformanceSalary(incentiveWage.getIncentivePerformanceScore() * factor);
        incentiveWageService.updateById(incentiveWage);
    }
}
