package top.alvinsite.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.jexl3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.dao.salary.LevelFactorDao;
import top.alvinsite.demo.dao.salary.WorkloadTargetDao;
import top.alvinsite.demo.model.entity.salary.LevelFactor;
import top.alvinsite.demo.model.entity.salary.WorkloadTarget;
import top.alvinsite.demo.model.enums.ProjectLevel;
import top.alvinsite.demo.utils.ExcelUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private LevelFactorDao levelFactorDao;

    @Autowired
    private WorkloadTargetDao workloadTargetDao;


    @RequestMapping
    public Double index() {
        JexlContext jexlContext = new MapContext();
        jexlContext.set("job_subsidy", 10.0f);
        jexlContext.set("y", 3.0f);
        jexlContext.set("z", 2.0f);
        String expression = " 2job_subsidy + y";
        JexlEngine jexlEngine = new JexlBuilder().create();
        JexlExpression jexlExpression = jexlEngine.createExpression(expression);
        return (Double) jexlExpression.evaluate(jexlContext);
    }

    @RequestMapping("export-excel")
    public void writeExcel(HttpServletRequest request, HttpServletResponse response) {
        List<LevelFactor> levelFactors = levelFactorDao.findAll();
        List<WorkloadTarget> workloadTargets = workloadTargetDao.findAll();

        // ExcelUtils.writeExcel(response, levelFactors, LevelFactor.class);

        new ExcelUtils.Builder()
                .addSheet("sheet1", levelFactors, LevelFactor.class)
                .addSheet("sheet2", workloadTargets, WorkloadTarget.class)
                .setResponse(response)
                .build();
    }

    @RequestMapping("mybatis-plus")
    public String mybatisplus() {
        List<LevelFactor> list = levelFactorDao.selectList(null);
        for (LevelFactor item : list) {
            log.info(String.valueOf(item));
        }
        return "success";
    }
    @RequestMapping("mybatis-plus-2")
    public String mybatisplus2() {
        List<LevelFactor> list = levelFactorDao.findAll();
        for (LevelFactor item : list) {
            log.info(String.valueOf(item));
        }
        return "success";
    }

    @RequestMapping("mybatis-plus-3")
    public String mybatisplus3() {
        LevelFactor item = levelFactorDao.findOne(29);
        log.info(String.valueOf(item));
        return "success";
    }

    @RequestMapping("test3")
    public ProjectLevel test3(ProjectLevel level) {
        return level;
    }
}
