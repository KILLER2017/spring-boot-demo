package top.alvinsite.framework.springsecurity.provider;

import cn.edu.dgut.service.CasService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import top.alvinsite.framework.springsecurity.token.CasAuthenticationToken;

import java.util.Map;


@Slf4j
@Data
public class CasAuthenticationProvider implements AuthenticationProvider {

    private CasService casService;

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        log.info("running CasAuthenticationProvider.authenticate");

        CasAuthenticationToken authenticationToken = (CasAuthenticationToken) authentication;

        String token = (String) authenticationToken.getPrincipal();
        String remoteAddress = ((WebAuthenticationDetails)authenticationToken.getDetails()).getRemoteAddress();
        String username = getCasUserInfo(token, remoteAddress);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // 此时鉴权成功后，应当重新 new 一个拥有鉴权的 authenticationResult 返回
        CasAuthenticationToken authenticationResult = new CasAuthenticationToken(userDetails, userDetails.getAuthorities());

        authenticationResult.setDetails(authenticationToken.getDetails());

        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CasAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private String getCasUserInfo(String token, String userIp) {
        Map<String, String> result = casService.casUserInfo(token, userIp);
        log.info("dgut cas service check token: " + result);

        if (null != result.get("username")) {
            return result.get("username");
        } else {
            log.error(String.format("获取CAS用户信息失败, token: {}, result: {}", token, result));
            throw new UsernameNotFoundException("用户不存在");
        }

    }
}
