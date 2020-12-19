package top.alvinsite.demo.controller.salary;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.alvinsite.demo.model.entity.salary.SalarySummary;
import top.alvinsite.demo.model.params.Page;
import top.alvinsite.demo.model.params.PerformanceQuery;
import top.alvinsite.demo.model.vo.salary.PeopleListVo;
import top.alvinsite.demo.service.salary.SalarySummaryService;

import javax.el.MethodNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static top.alvinsite.utils.BeanUtils.transformFrom;

/**
 * @author Alvin
 */
@RestController
@RequestMapping("salary/summary")
public class SalarySummaryController {

    @Autowired
    private SalarySummaryService salarySummaryService;

    @GetMapping
    public PageInfo<PeopleListVo> getPageData(PerformanceQuery query, Page page) {
        PageHelper.startPage(page);
        List<SalarySummary> salarySummaries = salarySummaryService.findAll(query);
        List<PeopleListVo> peopleListVos = salarySummaries.stream().map(item -> transformFrom(item, PeopleListVo.class)).collect(Collectors.toList());

        return new PageInfo<>(peopleListVos);
    }

    public void importExcel(PerformanceQuery query, MultipartFile file) {

    }

    public void exportExcel() {

    }

    public void getTemplate() {

    }

    /**
     * 不允许调用该类的此方法
     * @param record
     */
    public void update(PeopleListVo record) {
        throw new MethodNotFoundException();
    }
}
