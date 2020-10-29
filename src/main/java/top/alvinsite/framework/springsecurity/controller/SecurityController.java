package top.alvinsite.framework.springsecurity.controller;

import cn.edu.dgut.service.CasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.model.support.BaseResponse;
import top.alvinsite.demo.model.vo.UserInfoVO;
import top.alvinsite.framework.springsecurity.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static top.alvinsite.demo.utils.BeanUtils.transformFrom;

/**
 * @author Alvin
 */

@Slf4j
@RestController
@RequestMapping("auth")
public class SecurityController {

    @Autowired
    private CasService casService;

    /**
     * 获取用户认证信息 方法一
     * @return
     */
    @RequestMapping("getAuthentication-1")
    public Object getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户认证信息 方法二
     * @param authentication
     * @return
     */
    @RequestMapping("getAuthentication-2")
    public Object getAuthentication(Authentication authentication) {
        return authentication;
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

    /**
     * @PreAuthorize、@PostAuthorize、@PreFilter、@PostFilter
     */

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

    @GetMapping("getUserInfo")
    public BaseResponse getUserInfo(@AuthenticationPrincipal User userDetails) {
        UserInfoVO userInfoVO = transformFrom(userDetails, UserInfoVO.class);
        userInfoVO.setDepartment(userDetails.getDepartment());
        userInfoVO.setAccount(userDetails.getUsername());

        return BaseResponse.ok("操作成功", userInfoVO);
    }
}
