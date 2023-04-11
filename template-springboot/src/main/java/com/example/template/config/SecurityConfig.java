package com.example.template.config;

import com.alibaba.fastjson.JSONObject;
import com.example.template.common.ResultBody;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * SpringSecurity配置类
 *
 * @author: mai
 * @since: 2023/4/11
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/api/auth/login")
                .successHandler(this::onAuthenticationSuccess)
                .failureHandler(this::onAuthenticationFailure)
                .and()
                .logout()
                .logoutUrl("/api/auth/logout")
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(this::onAuthenticationFailure)
                .and()
                .build();
    }

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSONObject.toJSONString(ResultBody.error("401", exception.getMessage())));
    }

    private void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSONObject.toJSONString(ResultBody.success("登录成功")));
    }

}
