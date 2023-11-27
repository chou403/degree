package com.second.main.workbook.blog.config;//package com.second.project.other.blog.config;
//
//import com.second.common.interceptor.AuthInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * 拦截器
// */
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//
//    /**
//     * 添加拦截器
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //登录操作不拦截
//        registry.addInterceptor(new AuthInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/sys/login")
//                .excludePathPatterns("/sys/register")
//                .excludePathPatterns("/swagger-ui.html/**")
//                .excludePathPatterns("/v2/**")
//                .excludePathPatterns("/webjars/**")
//                .excludePathPatterns("/swagger-resources/**")
//                .excludePathPatterns("/csrf/**");
//    }
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOriginPatterns("*")
//                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
//                .maxAge(3600)
//                .allowCredentials(true);
//    }
//}
//
