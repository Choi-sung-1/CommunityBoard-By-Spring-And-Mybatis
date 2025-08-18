package com.project.toyProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())              // CSRF 비활성화 -->스프링 세큐리티
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()              // 모든 요청 허용
                )
                .formLogin(form -> form.disable());        // 기본 로그인 폼 비활성화 -->스프링 세큐리티 로그인 폼

        return http.build();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
