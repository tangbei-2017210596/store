package com.tb.store.config;

import com.tb.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoginInterceptorConfigure implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> allow = new ArrayList<>();
        allow.add("/bootstrap3/**");
        allow.add("/css/**");
        allow.add("/image/**");
        allow.add("/js/**");
        allow.add("/web/register.html");
        allow.add("/web/login.html");
        allow.add("/web/index.html");
        allow.add("/web/product.html");
        allow.add("/users/reg");
        allow.add("/users/login");
        allow.add("/districts/**");
        allow.add("/products/**");
        allow.add("/alipay/**");
        allow.add("/web/alipay/**");
        HandlerInterceptor loginInterceptor = new LoginInterceptor();
        registry.addInterceptor(loginInterceptor).excludePathPatterns(allow);
    }
}
