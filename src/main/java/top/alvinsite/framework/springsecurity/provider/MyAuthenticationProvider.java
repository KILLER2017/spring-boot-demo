package top.alvinsite.framework.springsecurity.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 账号密码登录自定义验证逻辑 Provider
 * @author Administrator
 */

@Slf4j
@Component
public class MyAuthenticationProvider extends DaoAuthenticationProvider {

    public MyAuthenticationProvider(@Qualifier("jwtUserService") UserDetailsService userDetailsService) {
        this.setUserDetailsService(userDetailsService);
        this.setPasswordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return true;
                // return encodedPassword.equals(rawPassword.toString());
            }
        });
    }

    /**
     * 附加认证过程
     * @param userDetails
     * @param authentication
     * @throws AuthenticationException
     */
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        log.info("进行自定义登录校验");

        /*
        // 编写更多校验逻辑
        MyWebAuthenticationDetails details = (MyWebAuthenticationDetails) authentication.getDetails();
        if (!details.getImageCodeIsRight()) {
            throw new VerificationCodeException();
        }
        */


        // 调用父类方法校验密码
        super.additionalAuthenticationChecks(userDetails, authentication);
    }
}
