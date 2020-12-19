package top.alvinsite.demo.controller.salary;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.alvinsite.demo.model.entity.salary.JobSubsidy;
import top.alvinsite.demo.model.params.Page;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.salary.JobSubsidyUpdateParam;
import top.alvinsite.demo.service.salary.JobSubsidyService;
import top.alvinsite.utils.ExcelUtils;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static top.alvinsite.utils.BeanUtils.transformFrom;

/**
 * 岗位津贴控制器
 * @author Alvin
 */
@RestController
@RequestMapping("salary/subsidy")
public class JobSubsidyController {

    @Autowired
    private JobSubsidyService jobSubsidyService;

    /**
     * 列表数据接口
     * @param query 查询过滤参数
     * @param page 分页参数
     * @return 岗位津贴列表数据
     */
    @GetMapping
    public PageInfo<JobSubsidy> getPageData(PerformanceQuery query, Page page) {
        PageHelper.startPage(page);
        List<JobSubsidy> jobSubsidies = jobSubsidyService.findAll(query);
        return new PageInfo<>(jobSubsidies);
    }

    /**
     * Excel表导入岗位津贴数据
     * @param query 过滤参数
     * @param file 上传文件
     */
    @PostMapping("importExcel")
    public void importExcel(PerformanceQuery query, @RequestParam(value="uploadFile") MultipartFile file) {
        List<JobSubsidy> list = ExcelUtils.readExcel("", JobSubsidy.class, file);
        list.stream().map(item-> {
            item.setYear(query.getYear());

            // 查询是否已存在记录
            JobSubsidy oldRecord = jobSubsidyService.getOne(Wrappers.<JobSubsidy>lambdaQuery()
                    .eq(JobSubsidy::getYear, query.getYear()).eq(JobSubsidy::getAccount, item.getAccount())
                    , false);
            if (oldRecord != null) {
                item.setId(oldRecord.getId());
            }

            // 计算并设置个人岗位津贴
            item.setSubsidy(item.getSubsidyFactor() * item.getLength());
            return item;
        }).collect(Collectors.toList());
        jobSubsidyService.saveOrUpdateBatch(list);
    }

    /**
     * Excel表导出岗位津贴数据
     * @param query 查询过滤参数
     * @param response 请求响应
     */
    @PostMapping("exportExcel")
    public void exportExcel(PerformanceQuery query, HttpServletResponse response) {
        List<JobSubsidy> jobSubsidies = jobSubsidyService.findAll(query);

        Workbook workbook = new ExcelUtils.Builder()
                .addSheet("sheet1", jobSubsidies, JobSubsidy.class)
                .build();
        ExcelUtils.buildExcelDocument("岗位津贴.xlsx", workbook, response);
    }


    /**
     * 获取数据导入Excel模板
     * @param response 请求响应
     */
    @PostMapping("template")
    public void getTemplate(HttpServletResponse response) {
        Workbook workbook = new ExcelUtils.Builder()
                .addSheet("sheet1", new ArrayList<>(), JobSubsidy.class)
                .build();
        ExcelUtils.buildExcelDocument("岗位津贴导入模板.xlsx", workbook, response);
    }

    /**
     * 岗位津贴更新接口
     * @param param 岗位津贴
     */
    @PutMapping
    public void update(@Valid @RequestBody JobSubsidyUpdateParam param) {
        JobSubsidy jobSubsidy = transformFrom(param, JobSubsidy.class);
        assert jobSubsidy != null;
        jobSubsidy.setSubsidy(jobSubsidy.getSubsidyFactor() * jobSubsidy.getLength());
        jobSubsidyService.updateById(jobSubsidy);
    }

}
