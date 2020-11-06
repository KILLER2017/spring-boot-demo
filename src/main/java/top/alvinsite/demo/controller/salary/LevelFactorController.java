package top.alvinsite.demo.controller.salary;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.alvinsite.demo.dao.salary.LevelFactorDao;
import top.alvinsite.demo.model.entity.salary.LevelFactor;
import top.alvinsite.demo.model.params.LevelFactorParam;
import top.alvinsite.demo.model.params.Page;
import top.alvinsite.utils.ExcelUtils;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("salary/level-factor")
public class LevelFactorController {
    @Autowired
    private LevelFactorDao levelFactorDao;

    @GetMapping
    public PageInfo<LevelFactor> list(Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        return new PageInfo<>(levelFactorDao.findAll());
    }

    @PostMapping
    public void save(@RequestBody LevelFactor levelFactor) {
        levelFactorDao.save(levelFactor);
    }

    @PostMapping("importExcel")
    public void importExcel(@RequestParam(value="uploadFile") MultipartFile file) {
        long t1 = System.currentTimeMillis();
        List<LevelFactor> list = ExcelUtils.readExcel("", LevelFactor.class, file);
        long t2 = System.currentTimeMillis();
        System.out.printf("read over! cost: %sms\n", t2 - t1);

        list.forEach(
            item -> {
                LevelFactor oObject = levelFactorDao.findOneByTypeAndLevel(new LevelFactorParam(item.getType(), item.getType()));
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
