package top.alvinsite.demo.controller.salary;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.alvinsite.demo.model.params.Page;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.service.salary.SalaryService;
import top.alvinsite.utils.ExcelUtils;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static top.alvinsite.utils.BeanUtils.transformFrom;
import static top.alvinsite.utils.BeanUtils.transformFromInBatch;

/**
 * @author Alvin
 */
@Validated
public abstract class AbstractSalaryController<M extends SalaryService<T>, T, V> {

    @Autowired
    protected M baseService;

    /**
     * 获取实体类Class对象
     * @return 实体类Class对象
     */
    protected abstract Class<T> getEntityClass();

    /**
     * 获取更新参数实体类Class对象
     * @return 更新参数实体类Class对象
     */
    protected abstract Class<V> getParamClass();

    /**
     * 获取导出Excel表文件名
     * @return 导出Excel表文件名
     */
    protected abstract String getOutputExcelName();

    /**
     * 获取Excel导入模板文件名
     * @return Excel导入模板文件名
     */
    protected abstract String getExcelTemplateName();

    /**
     * 列表页数据接口
     * @param query 查询参数
     * @param page 分页参数
     * @return 列表数据
     */
    @GetMapping
    public PageInfo<T> getPageData(@Valid PerformanceQuery query, Page page) {
        PageHelper.startPage(page);
        List<T> list = baseService.findAll(query);
        return new PageInfo<>(list);
    }

    /**
     * 从Excel表导出数据时，调用该方法对行数据进行处理

     * @param query 查询参数
     * @param entity 实体
     * @return 处理后的实体
     */
    protected abstract T handle(PerformanceQuery query, T entity);

    /**
     * Excel数据导入接口
     * @param query 查询过滤参数
     * @param file Excel文件
     */
    @PostMapping("importExcel/{year}")
    @Transactional(rollbackFor = Exception.class)
    public void importExcel(@Valid PerformanceQuery query, @RequestParam(value="uploadFile") MultipartFile file) {
        List<V> excelData = ExcelUtils.readExcel("", getParamClass(), file);
        List<T> list = transformFromInBatch(excelData, getEntityClass());
        list = list.stream()
                .map(item -> handle(query, item))
                .collect(Collectors.toList());
        baseService.saveOrUpdateBatch(list);
    }

    /**
     * Excel数据导出接口
     */
    @GetMapping("exportExcel")
    public void exportExcel(@Valid PerformanceQuery query, HttpServletResponse response) {
        List<T> list = baseService.findAll(query);

        Workbook workbook = new ExcelUtils.Builder()
                .addSheet("sheet1", list, getEntityClass())
                .build();
        ExcelUtils.buildExcelDocument(getOutputExcelName(), workbook, response);
    }

    /**
     * 获取导入模板
     */
    @GetMapping("template")
    public void getTemplate(HttpServletResponse response) {
        Workbook workbook = new ExcelUtils.Builder()
                .addSheet("sheet1", new ArrayList<>(), getParamClass())
                .build();
        ExcelUtils.buildExcelDocument(getExcelTemplateName(), workbook, response);
    }

    /**
     * 数据更新接口
     * @param record 更新参数实体
     */
    @PutMapping
    public void update(@Valid @RequestBody V record) {
        T item = transformFrom(record, getEntityClass());
        baseService.updateById(item);
    }

    @PostMapping("put")
    public void updateByPost(@Valid @RequestBody V record) {
        update(record);
    }

}
