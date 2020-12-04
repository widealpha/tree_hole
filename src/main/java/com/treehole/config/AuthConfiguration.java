package com.treehole.config;

import com.treehole.filter.AuthFilter;
import com.treehole.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
public class AuthConfiguration implements WebMvcConfigurer {
    @Autowired
    AuthInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/treehole/user/login")
                .excludePathPatterns("/treehole/user/register")
                .excludePathPatterns("/treehole/article/**")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/register")
                .excludePathPatterns("/article/**");
    }
    @Bean
    public FilterRegistrationBean<AuthFilter> paramFilter(){
        FilterRegistrationBean<AuthFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new AuthFilter());
        bean.addUrlPatterns("/**");
        bean.addUrlPatterns("/treehole/**");
        bean.setOrder(0);
        return bean;
    }
}
