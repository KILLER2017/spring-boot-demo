package top.alvinsite.demo.controller.salary;


import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import top.alvinsite.demo.model.params.Page;
import top.alvinsite.demo.model.params.PerformanceQuery;

/**
 * @author Alvin
 */
public abstract class BaseController<T, M> {


    /**
     * 列表页数据接口
     * @param query
     * @param page
     * @return
     */
    @GetMapping
    public abstract PageInfo<T> getPageData(PerformanceQuery query, Page page);

    /**
     * Excel数据导入接口
     * @param query 查询过滤参数
     * @param file Excel文件
     */
    @PostMapping("importExcel")
    public abstract void importExcel(PerformanceQuery query, @RequestParam(value="uploadFile") MultipartFile file);

    /**
     * Excel数据导出接口
     */
    @PostMapping("exportExcel")
    public abstract void exportExcel();

    /**
     * 获取导入模板
     */
    @PostMapping("template")
    public abstract void getTemplate();

    /**
     * 数据更新接口
     * @param record
     */
    @PutMapping
    public abstract void update(M record);

}
