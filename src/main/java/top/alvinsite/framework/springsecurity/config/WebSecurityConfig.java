package top.alvinsite.framework.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import top.alvinsite.framework.springsecurity.filter.OptionsRequestFilter;

import java.util.Arrays;


/**
 * @author Administrator
 */
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    protected UsernamePasswordLoginConfigurer usernamePasswordLoginConfigurer;

    @Autowired
    protected JwtLoginConfigurer jwtLoginConfigurer;

    @Autowired
    protected LogoutHandler logoutHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/image/**").permitAll()
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/article/**").hasRole("USER")
                .anyRequest().authenticated()
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
                .apply(usernamePasswordLoginConfigurer)
                .and()
                .apply(jwtLoginConfigurer)
                .and()
                .logout()
//		        .logoutUrl("/logout")   //默认就是"/logout"
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                .and()
                .sessionManagement().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 已在configurer中注册provider，此处无需再次注册
        // auth.authenticationProvider(daoAuthenticationProvider()).authenticationProvider(jwtAuthenticationProvider());
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
