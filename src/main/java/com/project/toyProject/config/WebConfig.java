package com.project.toyProject.config;

import com.project.toyProject.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/profileImages/**") // 웹 경로
                .addResourceLocations("file:/Users/ChoiSungWon/Documents/Develope/spring/file/profile/"); // 실제 경로
        registry.addResourceHandler("/postImages/**")
                .addResourceLocations("file:/Users/ChoiSungWon/Documents/Develope/spring/file/post/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/","/member/duplicate","/api/kakaomap","/post/list","/member/join","/member/login","/member/logout","/js/**","/css/**","/*.ico","/error");
    }

}

