package com.second.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 拦截器，登录检查
 */
@Component
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        String token = request.getHeader("token");
//        log.info("request url :{}", request.getRequestURL());
//        try {
//            if (StringUtils.isBlank(token)) {
//                log.error("****** 用户令牌为空！******");
//                response.setStatus(ResultCode.SIGNATURE_NOT_TOKEN.getResultCode());
//                return false;
//            }
//            boolean checkResult = JwtUtil.checkToken(token);
//            if (!checkResult) {
//                log.error("****** 用户令牌无效！******");
//                response.setStatus(ResultCode.SIGNATURE_NOT_TOKEN.getResultCode());
//            }
//            return checkResult;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        //     return false;
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }

}
