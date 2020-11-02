package top.alvinsite.framework.springsecurity.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import top.alvinsite.demo.config.properties.AppProperties;
import top.alvinsite.framework.springsecurity.filter.OptionsRequestFilter;

import java.util.Arrays;


/**
 * @author Administrator
 */
@Slf4j
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AppProperties appProperties;

    @Autowired
    protected UsernamePasswordLoginConfigurer usernamePasswordLoginConfigurer;

    @Autowired
    protected JwtLoginConfigurer jwtLoginConfigurer;

    @Autowired
    protected CasLoginConfigurer casLoginConfigurer;

    @Autowired
    protected LogoutHandler logoutHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests()
                // .antMatchers("/image/**").permitAll()
                .antMatchers("/performance/**").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/salary/**").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/auth/permission/**").hasRole("ADMIN")
                .antMatchers("/auth/getUserInfo").hasAnyRole("ADMIN", "MANAGER")
                //.antMatchers("/actuator/**").hasRole("ADMIN")
                //.antMatchers("/instances/**").hasRole("ADMIN")
                //.antMatchers("/actuator/**").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement().disable()
                .cors()
                .and()
                .headers().addHeaderWriter(new StaticHeadersWriter(Arrays.asList(
                new Header("Access-control-Allow-Origin","*"),
                new Header("Access-Control-Expose-Headers","Authorization"))))
                .and()
                .addFilterAfter(new OptionsRequestFilter(), CorsFilter.class)
                .apply(jwtLoginConfigurer)
                .and()
                .apply(casLoginConfigurer)
                .and()
                .logout()
		        .logoutUrl("/auth/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                .and()
                .sessionManagement().disable();

        if (appProperties.isEnableUsernamePasswordLogin()) {
            log.info("启用账号密码登录");
            http.apply(usernamePasswordLoginConfigurer);
        }
    }


    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","HEAD", "OPTION"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.addExposedHeader("Authorization");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
