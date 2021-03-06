package top.alvinsite.framework.springsecurity.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import top.alvinsite.framework.springsecurity.service.JwtUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 */

@Component
public class JsonLoginSuccessHandler implements AuthenticationSuccessHandler{

	@Autowired
	private JwtUserService jwtUserService;
	
	public JsonLoginSuccessHandler(JwtUserService jwtUserService) {
		this.jwtUserService = jwtUserService;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {
		// String token = jwtUserService.saveUserLoginInfo((UserDetails)authentication.getPrincipal());
		// response.setHeader("Authorization", token);
	}
	
}
