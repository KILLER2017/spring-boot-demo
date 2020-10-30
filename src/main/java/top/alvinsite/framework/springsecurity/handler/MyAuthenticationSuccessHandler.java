package top.alvinsite.framework.springsecurity.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import top.alvinsite.demo.model.support.BaseResponse;
import top.alvinsite.framework.springsecurity.service.JwtUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author Administrator
 */
@Slf4j
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtUserService jwtUserService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        log.info("登录成功, {}", objectMapper.writeValueAsString(authentication));

        response.setContentType("application/json;charset=UTF-8");

        String token = jwtUserService.saveUserLoginInfo((UserDetails)authentication.getPrincipal());
        HashMap<String, String> data = new HashMap<>(1);
        data.put("authorization", token);
        BaseResponse baseResponse = BaseResponse.ok("登录成功", data);

        response.getWriter().write(objectMapper.writeValueAsString(baseResponse));
    }
}
