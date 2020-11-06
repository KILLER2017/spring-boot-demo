package top.alvinsite.demo.controller.salary;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.alvinsite.demo.dao.salary.WorkloadTargetDao;
import top.alvinsite.demo.model.entity.salary.WorkloadTarget;
import top.alvinsite.demo.model.params.Page;
import top.alvinsite.demo.model.params.WorkloadTargetParam;
import top.alvinsite.utils.ExcelUtils;

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
    public PageInfo<WorkloadTarget> list(Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        return new PageInfo<>(workloadTargetDao.findAll());
    }

    @PostMapping
    public void save(@RequestBody WorkloadTarget workloadTarget) {
        workloadTargetDao.save(workloadTarget);
    }

    @PostMapping("importExcel")
    public void importExcel(@RequestParam(value="uploadFile") MultipartFile file) {
        long t1 = System.currentTimeMillis();
        List<WorkloadTarget> list = ExcelUtils.readExcel("", WorkloadTarget.class, file);
        long t2 = System.currentTimeMillis();
        System.out.println(String.format("read over! cost:%sms", (t2 - t1)));
        list.forEach(
            item ->{
                WorkloadTarget oObject = workloadTargetDao.findOneByLevelAndPostType(new WorkloadTargetParam(item.getLevel(), item.getType()));
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
