package com.second.common.config;

import com.second.common.aop.interceptor.AuthInterceptor;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] swaggerExcludePathPatterns = {
                "/doc.html", "/swagger**/**", "/swagger-resources/**", "/webjars/**", "/v3/**"
        };
        // 登录操作不拦截
        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/sys/login")
                .excludePathPatterns("/sys/register")
                .excludePathPatterns(swaggerExcludePathPatterns)
                .excludePathPatterns("/csrf/**");
    }

    /**
     * 静态资源映射
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("favicon.ico").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600)
                .allowCredentials(true);
    }

//    @Bean
//    public FilterRegistrationBean<Filter> traceFilter() {
//        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
////        TraceFilter traceFilter = new TraceFilter();
////        registration.setFilter(traceFilter);
//        registration.addUrlPatterns("/*");
//        return registration;
//    }
}

