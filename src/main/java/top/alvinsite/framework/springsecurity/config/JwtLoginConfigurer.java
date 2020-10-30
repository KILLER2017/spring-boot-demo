package top.alvinsite.framework.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.stereotype.Component;
import top.alvinsite.framework.springsecurity.filter.JwtAuthenticationFilter;
import top.alvinsite.framework.springsecurity.provider.JwtAuthenticationProvider;

/**
 * @author Alvin
 */
@Component
public class JwtLoginConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    /**
     * 过滤器
     */
    private final JwtAuthenticationFilter authenticationFilter;

    /**
     * 登录校验逻辑
     */
    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    /**
     * 登录成功处理器
     */
    @Qualifier("jsonLoginSuccessHandler")
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    /**
     * 登录失败处理器
     */
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    public JwtLoginConfigurer() {
        this.authenticationFilter = new JwtAuthenticationFilter();
        authenticationFilter.setPermissiveUrl("/auth/logout");
        authenticationFilter.setPermissiveUrl("/auth/protectedLogin");
    }

    @Override
    public void configure(HttpSecurity http) {
        authenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        authenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        authenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);

        JwtAuthenticationFilter filter = postProcess(authenticationFilter);

        // 将过滤器添加到链
        http.authenticationProvider(jwtAuthenticationProvider).addFilterBefore(filter, LogoutFilter.class);
    }

}
