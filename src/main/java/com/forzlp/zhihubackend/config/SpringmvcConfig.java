package com.forzlp.zhihubackend.config;

import com.forzlp.zhihubackend.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class SpringmvcConfig implements WebMvcConfigurer {

    private LoginInterceptor loginInterceptor;
    @Autowired
    public SpringmvcConfig(LoginInterceptor loginInterceptor) {
        this.loginInterceptor = loginInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加拦截器loginInterceptor,对路径 /login下的方法进行拦截
        registry.addInterceptor(loginInterceptor).addPathPatterns("/user/getUser");
    }
}