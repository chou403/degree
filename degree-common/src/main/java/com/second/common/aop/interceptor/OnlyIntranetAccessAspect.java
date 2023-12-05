package com.second.common.aop.interceptor;

import com.second.common.aop.advice.BizException;
import com.second.common.bean.HttpEnum;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/11/22
 * {@code @description} 内外网访问权限判断
 */
@Slf4j
@Aspect
@Component
public class OnlyIntranetAccessAspect {

    @Pointcut("@within(com.second.common.aop.annotations.OnlyIntranetAccess)")
    public void onlyIntranetAccessOnClass() {
    }

    @Pointcut("@annotation(com.second.common.aop.annotations.OnlyIntranetAccess)")
    public void onlyIntranetAccessOnMethod() {
    }

    @Before(value = "onlyIntranetAccessOnMethod() || onlyIntranetAccessOnClass()")
    public void before() {
        HttpServletRequest hsr = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String from = hsr.getHeader("from");
        if (!StringUtils.isEmpty(from) && "public".equals(from)) {
            log.error("This api is only allowed invoked by intranet source");
            throw new BizException(HttpEnum.NOT_PERMISSION.getMessage());
        }
    }
}
