package top.alvinsite.demo.controller.salary;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.alvinsite.demo.dao.salary.LevelFactorDao;
import top.alvinsite.demo.model.entity.salary.LevelFactor;
import top.alvinsite.demo.model.entity.salary.WorkloadTarget;
import top.alvinsite.demo.model.params.LevelFactorParam;
import top.alvinsite.demo.model.params.Page;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.salary.LevelFactorUpdateParam;
import top.alvinsite.demo.service.salary.LevelFactorService;
import top.alvinsite.utils.ExcelUtils;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static top.alvinsite.utils.BeanUtils.transformFrom;

@Slf4j
@RestController
@RequestMapping("salary/level-factor")
public class LevelFactorController {

    @Autowired
    private LevelFactorService levelFactorService;

    @Autowired
    private LevelFactorDao levelFactorDao;

    @GetMapping
    public PageInfo<LevelFactor> getPageData(@Valid PerformanceQuery query, Page page) {
        PageHelper.startPage(page);
        return new PageInfo<>(levelFactorDao.findAll(query));
    }

    @PostMapping
    public void save(@RequestBody LevelFactor levelFactor) {
        levelFactorDao.save(levelFactor);
    }

    @PostMapping("importExcel/{department}")
    public void importExcel(PerformanceQuery query, @RequestParam(value="uploadFile") MultipartFile file) {
        List<LevelFactor> list = ExcelUtils.readExcel("", LevelFactor.class, file);

        list.forEach(item -> {
            item.setYear(query.getYear());
            item.setDepartment(query.getDepartment());
            LevelFactorParam param = transformFrom(item, LevelFactorParam.class);
            LevelFactor oObject = levelFactorDao.findOneByTypeAndLevel(param);

            if (oObject != null) {
                item.setId(oObject.getId());
                levelFactorService.updateById(item);
            } else {
                levelFactorService.save(item);
            }
        });

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
    public void update(@Valid @RequestBody LevelFactorUpdateParam param) {
        LevelFactor record = transformFrom(param, LevelFactor.class);
        assert record != null;
        levelFactorService.updateById(record);
    }
}
