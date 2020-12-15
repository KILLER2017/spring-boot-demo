package top.alvinsite.demo.controller.salary;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.alvinsite.demo.dao.salary.LevelFactorDao;
import top.alvinsite.demo.model.entity.salary.LevelFactor;
import top.alvinsite.demo.model.params.LevelFactorParam;
import top.alvinsite.demo.model.params.Page;
import top.alvinsite.demo.model.params.SalaryQuery;
import top.alvinsite.framework.springsecurity.entity.User;
import top.alvinsite.utils.ExcelUtils;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("salary/level-factor")
public class LevelFactorController {
    @Autowired
    private LevelFactorDao levelFactorDao;

    @GetMapping
    public PageInfo<LevelFactor> list(@Valid SalaryQuery salaryQuery, Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        return new PageInfo<>(levelFactorDao.findAll(salaryQuery));
    }

    @PostMapping
    public void save(@RequestBody LevelFactor levelFactor) {
        levelFactorDao.save(levelFactor);
    }

    @PostMapping("importExcel/{department}")
    public void importExcel(@PathVariable String department, @RequestParam(value="uploadFile") MultipartFile file) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if ("manager".equals(user.getUserGroup()) && !Arrays.asList(user.getManageUnits()).contains(department)) {
            throw new IllegalArgumentException("部门参数错误，只能向您管理的部门导入数据");
        }

        List<LevelFactor> list = ExcelUtils.readExcel("", LevelFactor.class, file);


        list.forEach(
            item -> {
                item.setDepartment(department);
                LevelFactor oObject = levelFactorDao.findOneByTypeAndLevel(new LevelFactorParam(department, item.getType(), item.getType()));
                if (oObject != null) {
                    item.setId(oObject.getId());
                }

                levelFactorDao.save(item);
            }
        );
    }

    @PostMapping("outputExcel")
    public void outputExcel() {

    }
}
