package top.alvinsite.demo.controller.salary;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.alvinsite.demo.model.entity.salary.OvertimeWorkedSubsidy;
import top.alvinsite.demo.model.params.Page;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.params.salary.OvertimeWorkedSubsidyUpdateParam;
import top.alvinsite.demo.service.salary.OvertimeWorkedSubsidyService;

import java.util.List;

/**
 * 超课时津贴控制器
 * @author Alvin
 */
@RestController
@RequestMapping("salary/overtime-worked-subsidy")
public class OvertimeWorkedSubsidyController extends BaseController<OvertimeWorkedSubsidy,
        OvertimeWorkedSubsidyUpdateParam> {

    @Autowired
    private OvertimeWorkedSubsidyService overtimeWorkedSubsidyService;

    @Override
    public PageInfo<OvertimeWorkedSubsidy> getPageData(PerformanceQuery query, Page page) {
        PageHelper.startPage(page);
        List<OvertimeWorkedSubsidy> list = overtimeWorkedSubsidyService.findAll(query);
        return new PageInfo<>(list);
    }

    @Override
    public void importExcel(PerformanceQuery query, MultipartFile file) {

    }

    @Override
    public void exportExcel() {

    }

    @Override
    public void getTemplate() {

    }

    @Override
    public void update(OvertimeWorkedSubsidyUpdateParam record) {

    }
}
