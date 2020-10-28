package top.alvinsite.framework.springsecurity.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.model.vo.UserInfoVO;

/**
 * @author Alvin
 */

@RestController
@RequestMapping("test/security")
public class SecurityController {

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

    public UserInfoVO getUserInfo(@AuthenticationPrincipal UserDetails userDetails) {


        return null;
    }
}
