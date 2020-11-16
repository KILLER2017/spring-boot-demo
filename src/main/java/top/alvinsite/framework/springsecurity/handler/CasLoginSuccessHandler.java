package top.alvinsite.framework.springsecurity.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import top.alvinsite.framework.springsecurity.service.JwtUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Alvin
 */
@Component
public class CasLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Value("${app.domain}")
    private String domain;

    @Autowired
    private JwtUserService jwtUserService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String token = jwtUserService.saveUserLoginInfo((UserDetails)authentication.getPrincipal());
        response.sendRedirect(String.format("http://%s/Loading?authorization=%s", domain, token));
    }
}
