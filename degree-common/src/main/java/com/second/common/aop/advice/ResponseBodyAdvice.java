package com.second.common.aop.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;

import jakarta.annotation.Nullable;

/**
 * ResponseBodyAdvice 是对 Controller 返回的内容在 HttpMessageConverter 进行类型转换之前拦截，进行相应的处理操作后，再将结果返回给客户端。那这样就可以把统一包装的工作放到这个类里面。
 * {@code @author}  chouchou
 * {@code @date}    2023/5/4 17:49
 */
public interface ResponseBodyAdvice<T> {
    /**
     * 判断是否要交给 beforeBodyWrite 方法执行，ture：需要；false：不需要
     * {@code @date} 2023/5/4 17:50
     */
    boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType);

    /**
     * 对 response 进行具体的处理
     * {@code @date} 2023/5/4 17:50
     */
    @Nullable
    T beforeBodyWrite(@Nullable T body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response);

}
