package com.xihang.work.configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xihang.work.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @Author:Yankaikai
 * @Description:web安全登录控制端
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private UserRepository userRepository;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // 禁用csrf，不太安全
                .exceptionHandling()
                // 自定义认证响应，失败返回401
                .authenticationEntryPoint(new AjaxAuthenticationEntryPoint())
                .and()
                .authorizeRequests()
                // /api 下的接口都要登录权限
               // .mvcMatchers("/api/**").authenticated()
               // .mvcMatchers("/api/**").authenticated()
                .mvcMatchers(HttpMethod.POST,"/api/users/registry").permitAll()
                // 判断是否登录的接口不用认证
                .mvcMatchers(HttpMethod.GET, "/api/self").permitAll()
                .mvcMatchers("/**").permitAll()
                .anyRequest().authenticated().and()
                .formLogin()
                .failureHandler(new AjaxAuthenticationFailureHandler()) // 登录失败
                .successHandler(new AjaxAuthenticationSuccessHandler()) // 登录成功
                .and()
                .logout() // 退出登录
                .logoutSuccessHandler(new AjaxLogoutSuccessHandler()) // 退出登录成功
                .permitAll().and()
                .httpBasic();
    }
    /**
     * 自定义登录认证响应，失败返回401
     */
    private class  AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {
        @Override
        public void commence(
                HttpServletRequest httpServletRequest,
                HttpServletResponse httpServletResponse,
                AuthenticationException e
        ) throws IOException, ServletException {
            httpServletResponse.sendError(
                    HttpStatus.UNAUTHORIZED.value(),
                    e.getMessage());
        }
    }
    /**
     * 登录失败处理类
     */
    private class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {
        @Override
        public void onAuthenticationFailure(
                HttpServletRequest httpServletRequest,
                HttpServletResponse httpServletResponse,
                AuthenticationException e
        ) throws IOException, ServletException {
            httpServletResponse.sendError(
                    HttpStatus.UNAUTHORIZED.value(),
                    e.getMessage());
        }
    }
    /**
     * 登录成功处理类
     */
    private class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

        @Override
        public void onAuthenticationSuccess(
                HttpServletRequest httpServletRequest,
                HttpServletResponse httpServletResponse,
                Authentication authentication
        ) throws IOException, ServletException {
            // 登录成功返回登录用户的信息（json 格式）
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            mapper.writeValue(
                    httpServletResponse.getWriter(),
                    userRepository.findByUserName(authentication.getName())
            );
        }
    }
    /**
     * 登出成功处理类
     */
    private class AjaxLogoutSuccessHandler implements LogoutSuccessHandler {

        @Override
        public void onLogoutSuccess(
                HttpServletRequest httpServletRequest,
                HttpServletResponse httpServletResponse,
                Authentication authentication
        ) throws IOException, ServletException {
            // 退出登录成功响应204
            httpServletResponse.setStatus(HttpStatus.NO_CONTENT.value());
        }
    }
    /**
     * 登录认证过程
     *
     * @param auth AuthenticationManagerBuilder
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);// 对登录密码进行加盐哈希
    }
    @Bean
    public SessionRegistry sessionRegistry() {
        SessionRegistry sessionRegistry = new SessionRegistryImpl();
        return sessionRegistry;
    }
    @Bean
    public static ServletListenerRegistrationBean httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
    }
}
