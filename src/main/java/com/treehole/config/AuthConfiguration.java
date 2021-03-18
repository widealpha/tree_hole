package com.treehole.config;

import com.treehole.filter.AuthFilter;
import com.treehole.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
public class AuthConfiguration implements WebMvcConfigurer {
    @Autowired
    AuthInterceptor interceptor;

    @Autowired
    AuthFilter authFilter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/register")
                .excludePathPatterns("/user/forceChangePassword")
                .excludePathPatterns("/comment/articleComments")
                .excludePathPatterns("/comment/commentOnComment")
                .excludePathPatterns("/comment/getComment")
                .excludePathPatterns("/article/article")
                .excludePathPatterns("/article/uploadImage")
                .excludePathPatterns("/article/allArticles");
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> paramFilter() {
        FilterRegistrationBean<AuthFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(authFilter);
        bean.addUrlPatterns("/*");
        bean.setOrder(0);
        return bean;
    }
}
