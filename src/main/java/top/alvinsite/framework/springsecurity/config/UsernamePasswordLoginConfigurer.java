package top.alvinsite.framework.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.stereotype.Component;
import top.alvinsite.framework.springsecurity.filter.MyUsernamePasswordAuthenticationFilter;
import top.alvinsite.framework.springsecurity.handler.MyAuthenticationFailureHandler;

/**
 * @author Alvin
 */

@Component
public class UsernamePasswordLoginConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    /**
     * 用户名密码登录过滤器
     */
    private MyUsernamePasswordAuthenticationFilter authenticationFilter;

    /**
     * 登录校验处理逻辑
     */
    @Autowired
    private DaoAuthenticationProvider daoAuthenticationProvider;

    /**
     * 登录成功处理器
     */
    @Qualifier("myAuthenticationSuccessHandler")
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    /**
     * 登录失败处理器
     */
    @Autowired
    private MyAuthenticationFailureHandler authenticationFailureHandler;

    public UsernamePasswordLoginConfigurer() {
        this.authenticationFilter = new MyUsernamePasswordAuthenticationFilter();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        authenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        authenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        authenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        authenticationFilter.setSessionAuthenticationStrategy(new NullAuthenticatedSessionStrategy());

        MyUsernamePasswordAuthenticationFilter filter = postProcess(authenticationFilter);

        http.authenticationProvider(daoAuthenticationProvider).addFilterAfter(filter, LogoutFilter.class);
    }

}
