package top.alvinsite.framework.springsecurity.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.alvinsite.demo.model.support.BaseResponse;
import top.alvinsite.demo.model.vo.UserInfoVO;
import top.alvinsite.framework.springsecurity.entity.User;

import static top.alvinsite.demo.utils.BeanUtils.transformFrom;

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

    @GetMapping("getUserInfo")
    public BaseResponse getUserInfo(@AuthenticationPrincipal User userDetails) {
        UserInfoVO userInfoVO = transformFrom(userDetails, UserInfoVO.class);
        userInfoVO.setDepartment(userDetails.getDepartment());
        userInfoVO.setAccount(userDetails.getUsername());

        return BaseResponse.ok("操作成功", userInfoVO);
    }
}
