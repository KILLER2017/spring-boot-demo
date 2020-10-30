package top.alvinsite.demo.controller;

import cn.edu.dgut.service.CasService;
import cn.edu.dgut.utils.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.alvinsite.demo.dao.ResearcherDao;
import top.alvinsite.demo.dao.TeacherDao;
import top.alvinsite.demo.dao.auth.AdminDao;
import top.alvinsite.demo.dao.auth.ManagerDao;
import top.alvinsite.demo.exception.ForbiddenException;
import top.alvinsite.demo.model.dto.auth.AdminDTO;
import top.alvinsite.demo.model.dto.auth.ManagerDTO;
import top.alvinsite.demo.model.entity.Teacher;
import top.alvinsite.demo.model.support.UserInfo;
import top.alvinsite.demo.model.vo.UserInfoVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static top.alvinsite.demo.utils.BeanUtils.transformFrom;
import static top.alvinsite.demo.utils.BeanUtils.updateProperties;

/**
 * @author Alvin
 */
@Slf4j
// @RestController
// @RequestMapping("auth")
public class LoginController {

    @Value("${app.domain}")
    private String domain;

    @Autowired
    private CasService casService;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    ResearcherDao researcherDao;

    @Autowired
    TeacherDao teacherDao;

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private ManagerDao managerDao;

    /**
     * 用户登录
     * @param request 请求
     * @param response 响应
     * @throws IOException IO异常
     */
    @RequestMapping(value = "login", method = {RequestMethod.POST, RequestMethod.GET})
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取标记,判断是前台还是后台
        String state = request.getParameter("state");

        log.info("state:{}", state);
        if (StringUtils.isEmpty(state)){
            state = "home";
        }

        casService.redirectCasLogin(state, response);
    }

    @RequestMapping(value = "logout", method = {RequestMethod.POST,RequestMethod.GET})
    public void logout(HttpServletRequest request, HttpServletResponse response)throws IOException {
        // 获取标记,判断是前台还是后台
        String state = request.getParameter("state");
        String token=request.getParameter("authorization");

        log.info("state:{}",state);
        if (StringUtils.isEmpty(state)){
            state = "admin";
        }

        try {
            redisTemplate.delete(token);
        } catch (Exception e) {
            System.out.println("token不存在，直接重定向退出");
        }
        casService.redirectCasLogout(state, response);
    }


    /**
     * 中央认证回调接口
     * @param request 请求
     * @param httpResponse 响应
     * @throws IOException IO异常
     */
    @RequestMapping(value = "loginCallback", method = {RequestMethod.POST,RequestMethod.GET})
    public void loginCallback(HttpServletRequest request, HttpServletResponse httpResponse) throws IOException {
        log.info("=========================后台管理系统--进入--中央认证回调url===================");

        // 获取token
        String token = request.getParameter("token");

        String userIp = IpUtil.getRequestIp(request);

        log.info(userIp);

        Map<String, String> result = casService.casUserInfo(token, userIp);
        UserInfo userInfo = buildUserInfoByAccount(result.get("username"));

        if (userInfo == null || userInfo.getUserGroup() == null) {
            // throw new ForbiddenException("您使用的账号不能登录该系统");
            httpResponse.sendRedirect(String.format("http://%s/code.html", domain));
        }

        ValueOperations<String, UserInfo> opsForValue = redisTemplate.opsForValue();

        // 写入缓存，一小时过期
        assert userInfo != null;
        opsForValue.set(token, userInfo, 3600, TimeUnit.SECONDS);

        httpResponse.sendRedirect(String.format("http://%s/Loading?authorization=%s", domain, token));
    }

    private UserInfo buildUserInfoByAccount(String account) {

        Teacher teacher = teacherDao.findOne(account);
        if (teacher == null) {
            // throw new ForbiddenException("只允许使用教师账号登录");
            return null;
        }
        UserInfo userInfo = new UserInfo();
        updateProperties(teacher, userInfo);

        // 获取用户组：系统管理员、机构管理员
        AdminDTO adminDTO = adminDao.findOneByAccount(account);
        ManagerDTO managerDTO = managerDao.findOneByAccount(account);


        if (adminDTO != null) {
            userInfo.setUserGroup("admin");
        } else if (managerDTO != null) {
            userInfo.setUserGroup("manager");
            userInfo.setManageUnitId(managerDTO.getDepartment());

            String[] unitIds = managerDao.findUnitIdsByAccount(account);
            userInfo.setManageUnits(unitIds);
        }

        return userInfo;
    }

    @GetMapping("getUserInfo")
    public UserInfoVO getUserInfo(String authorization) {
        ValueOperations<String, UserInfo> opsForValue = redisTemplate.opsForValue();
        UserInfo userInfo = opsForValue.get(authorization);

        assert userInfo != null;

        UserInfoVO userInfoVO = transformFrom(userInfo, UserInfoVO.class);

        assert userInfoVO != null;

        userInfoVO.setDepartment(userInfo.getDepartment());

        return userInfoVO;
    }

    @GetMapping("protectedLogin")
    public Map<String, String> protectedLogin(@Valid @NonNull String account, @NonNull String password) {
        if (!log.isDebugEnabled()) {
            throw new ForbiddenException("访问错误");
        }

        log.info("=========================后台管理系统--进入--漏洞登录===================");
        ValueOperations<String, UserInfo> opsForValue = redisTemplate.opsForValue();

        String token = "dev-token-0000-0000-0001";
        UserInfo userInfo = buildUserInfoByAccount(account);
        if (userInfo == null || userInfo.getUserGroup() == null) {
            throw new ForbiddenException("您使用的账号不能登录该系统");
        }
        Map<String, String> result = new HashMap<>(2);
        result.put("authorization", token);
        opsForValue.set(token, userInfo);
        return result;
    }
}
