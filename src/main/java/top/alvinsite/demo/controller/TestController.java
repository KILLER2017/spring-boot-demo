package top.alvinsite.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.jexl3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.dao.salary.LevelFactorDao;
import top.alvinsite.demo.dao.salary.WorkloadTargetDao;
import top.alvinsite.demo.model.entity.salary.LevelFactor;
import top.alvinsite.framework.springsecurity.annotation.rest.AnonymousGetMapping;

import java.util.List;

/**
 * @author Alvin
 */
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



    @RequestMapping("mybatis-plus")
    public String mybatisplus() {
        List<LevelFactor> list = levelFactorDao.selectList(null);
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


    @AnonymousGetMapping("test3")
    public void test3(int count) throws Exception {
        long startTime = System.currentTimeMillis();
        int result = 0;
        for (int i = 0; i < count; i++) {
            result += i;
        }
        //获取结束时间
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
    }

    /**
     * 获取用户认证信息 方法三
     * @param userDetails
     * @return
     */
    @RequestMapping("getAuthentication-3")
    public Object getAuthentication(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }
}
