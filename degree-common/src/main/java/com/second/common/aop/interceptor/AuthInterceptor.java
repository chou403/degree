package com.second.common.aop.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.second.common.bean.StatusEnum;
import com.second.common.bean.reponse.Result;
import com.second.common.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 拦截器，登录检查
 */
@Component
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        String from = request.getHeader("from");
        String path = String.valueOf(request.getRequestURL());
        log.info("request url :{}", path);

        if (path.contains("/error")) {
            // 请求错误页面
            log.info("请求发生错误！跳转error地址");
            return true;
        }

        if (StringUtils.isEmpty(from) || !"public".equals(from)) {
            log.error("****** 网关令牌为空！******");
            result401(response);
            return false;
        }

//        try {
//            if (StringUtils.isBlank(token)) {
//                log.error("****** 用户令牌为空！******");
//                result401(response);
//                return false;
//            }
//            boolean checkResult = JwtUtil.checkToken(token);
//            if (!checkResult) {
//                log.error("****** 用户令牌无效！******");
//                result401(response);
//            }
//            return checkResult;
//        } catch (Exception e) {
//            log.error("请求错误:{}", e.getMessage());
//        }
        return true;
    }

    private void result401(HttpServletResponse response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            StringWriter sw = new StringWriter();

            Result result = Result.error(StatusEnum.AUTH_ERROR.getCode(), "访问验证超时，请重新登录");
            objectMapper.writeValue(sw, result);

            response.setStatus(StatusEnum.AUTH_ERROR.getCode());
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            log.info(sw.toString());
            writer.print(sw);
        } catch (Exception e) {
            log.error("访问验证超时，请重新登录");
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
        log.info("postHandle");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.info("afterCompletion");

    }

}
