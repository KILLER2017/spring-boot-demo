package top.alvinsite.framework.springsecurity.config;

import cn.edu.dgut.service.CasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import top.alvinsite.framework.springsecurity.filter.CasAuthenticationFilter;
import top.alvinsite.framework.springsecurity.handler.AuthenticationFailureHandler;
import top.alvinsite.framework.springsecurity.handler.AuthenticationSuccessHandler;
import top.alvinsite.framework.springsecurity.provider.CasAuthenticationProvider;

/**
 * 东莞理工学院中央认证登录
 */

@Component
public class CasAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private CasService casService;

    @Override
    public void configure(HttpSecurity http) {
        CasAuthenticationFilter authenticationFilter = new CasAuthenticationFilter();
        authenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        authenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        authenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);

        CasAuthenticationProvider authenticationProvider = new CasAuthenticationProvider();
        authenticationProvider.setCasService(casService);
        authenticationProvider.setUserDetailsService(userDetailsService);


        http.authenticationProvider(authenticationProvider)
                .addFilterAfter(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
