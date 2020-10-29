package top.alvinsite.framework.springsecurity.handler;


import cn.edu.dgut.service.CasService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import top.alvinsite.framework.springsecurity.service.JwtUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Alvin
 */
@Component
public class TokenClearLogoutHandler implements LogoutHandler {
	@Autowired
	private CasService casService;

	@Autowired
	private JwtUserService jwtUserService;
	
	public TokenClearLogoutHandler(JwtUserService jwtUserService) {
		this.jwtUserService = jwtUserService;
	}

	@SneakyThrows
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		String token = getJwtToken(request);
		DecodedJWT jwt = JWT.decode(token);
		clearToken(jwt.getKeyId());
		casService.redirectCasLogout("admin", response);
	}

	protected String getJwtToken(HttpServletRequest request) {
		String authInfo = request.getHeader("Authorization");

		if (StringUtils.isBlank(authInfo)) {
			authInfo = request.getParameter("authorization");
		}

		return StringUtils.removeStart(authInfo, "Bearer ");
	}

	protected void clearToken(String keyId) {
		if(StringUtils.isBlank(keyId)) {
			return;
		}

		jwtUserService.deleteUserLoginInfo(keyId);
	}
	
	protected void clearToken(Authentication authentication) {
		if(authentication == null) {
			return;
		}
		UserDetails user = (UserDetails)authentication.getPrincipal();
		if(user!=null && user.getUsername()!=null) {
			jwtUserService.deleteUserLoginInfo(user.getUsername());
		}
	}

}
