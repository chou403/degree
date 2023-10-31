package com.second.common.advice;

import com.second.common.bean.reponse.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.annotation.Nullable;

/**
 * controller 返回数据统一包装
 * 如果引入了swagger或knife4j的文档生成组件，这里需要仅扫描自己项目的包，否则文档无法正常生成
 * {@code @author}  chouchou
 * {@code @date}    2023/5/4 17:51
 */
@RestControllerAdvice(basePackages = "com.second.degree")
public class ResponseAdvice implements ResponseBodyAdvice<Object>{
    /**
     * 判断是否要交给 beforeBodyWrite 方法执行，ture：需要；false：不需要
     * {@code @date} 2023/5/4 17:50
     * @param returnType
     * @param converterType
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 如果不需要进行封装的，可以添加一些校验手段，比如添加标记排除的注解
        return false;
    }

    /**
     * 对 response 进行具体的处理
     * {@code @date} 2023/5/4 17:50
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     */
    @Nullable
    @Override
    public Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 提供一定的灵活度，如果body已经被包装了，就不进行包装
        if (body instanceof Result) {
            return body;
        }
        return Result.success(body);
    }
}
