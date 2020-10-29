package top.alvinsite.framework.springsecurity.provider;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.NonceExpiredException;
import org.springframework.stereotype.Component;
import top.alvinsite.framework.springsecurity.service.JwtUserService;
import top.alvinsite.framework.springsecurity.token.JwtAuthenticationToken;
import top.alvinsite.utils.JwtUtils;

import java.util.Calendar;

/**
 * JWT校验逻辑
 * @author Alvin
 */
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JwtUserService userService;

    public JwtAuthenticationProvider(JwtUserService userService) {
        this.userService = userService;
    }

    /**
     * 附加认证过程
     * @param userDetails 用户信息
     * @param jwt JSON WEB TOKEN
     * @throws AuthenticationException
     */
    protected void additionalAuthenticationChecks(UserDetails userDetails, DecodedJWT jwt) throws AuthenticationException {

        try {
            JwtUtils.verify(jwt);
        } catch (Exception e) {
            throw new BadCredentialsException("JWT token verify fail", e);
        }
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        DecodedJWT jwt = ((JwtAuthenticationToken)authentication).getToken();

        // 判断token是否过期
        if(jwt.getExpiresAt().before(Calendar.getInstance().getTime())) {
            throw new NonceExpiredException("Token expires");
        }

        String username = jwt.getSubject();
        UserDetails user = userService.getUserLoginInfo(username);

        // 判断用户是否存在
        // if(user == null || user.getPassword() == null) {
        if(user == null) {
            throw new NonceExpiredException("Token expires");
        }

        additionalAuthenticationChecks(user, jwt);

        return new JwtAuthenticationToken(user, jwt, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(JwtAuthenticationToken.class);
    }
}
