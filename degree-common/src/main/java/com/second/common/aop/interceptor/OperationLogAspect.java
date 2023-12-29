package com.second.common.aop.interceptor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.second.common.aop.annotations.OperationLog;
import com.second.common.bean.domain.OperationLogDTO;
import com.second.common.bean.domain.RequestDataDTO;
import com.second.common.service.OperationLogService;
import com.second.common.utils.JsonHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * {@code @author} chou401
 * {@code @date} 2022/12/2
 * {@code @className} OperationLogAspect
 * {@code @description} 操作切面
 */
@Aspect
@Component
@Slf4j
public class OperationLogAspect {

    private final OperationLogService operationLogService;

    public OperationLogAspect(OperationLogService operationLogService) {
        this.operationLogService = operationLogService;
    }

    @Pointcut("@annotation(com.second.common.aop.annotations.OperationLog)")
    public void buttonLogAspect() {
    }

    @AfterReturning("buttonLogAspect()")
    public void afterReturn(JoinPoint joinPoint) {
        try {
            Object[] args = getValidArgs(joinPoint.getArgs());
            OperationLog apiLog = getOperationLogInfo(joinPoint);
            RequestDataDTO request = getRequestData();

            OperationLogDTO dto = OperationLogDTO.of()
                    .setTitle(apiLog.title())
                    .setMethod(getMethodFullUrl(joinPoint))
                    .setBusinessType(apiLog.businessType().ordinal())
                    .setRequestUrl(request.getUri())
                    .setRequestMethod(request.getMethod())
                    .setRequestParams(JsonHelper.parseToJson(args))
                    .setSpecialParam(getSpecialParam(apiLog, args));

            operationLogService.saveLog(dto);
        } catch (Exception ignored) {
        }
    }

    public Object[] getValidArgs(Object[] args) {
        return Arrays.stream(args).filter(v -> !(v instanceof UriComponentsBuilder)).toArray();
    }

    public OperationLog getOperationLogInfo(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        return methodSignature.getMethod().getAnnotation(OperationLog.class);
    }

    public RequestDataDTO getRequestData() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        return new RequestDataDTO()
                .setUri(String.valueOf(request.getRequestURI()))
                .setMethod(request.getMethod());
    }

    public String getMethodFullUrl(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        return signature.getDeclaringTypeName() + "." + signature.getName();
    }

    public String getSpecialParam(OperationLog apiLog, Object[] args) {
        StringBuilder resultStr = new StringBuilder();
        String[] param = apiLog.special();
        if (param.length == 0) {
            return resultStr.toString();
        }

        try {
            List<Map> jsonList = JsonHelper.parseToObject(Arrays.toString(args), new TypeReference<>() {
            });

            for (Map o : jsonList) {
                if (!JsonHelper.isJsonObject(String.valueOf(o))) {
                    continue;
                }

                for (String s : param) {
                    if (o != null && StringUtils.isNotEmpty((CharSequence) o.get(s))) {
                        resultStr.append(o.get(s)).append(",");
                    }
                }
                if (StringUtils.isNotEmpty(resultStr)) {
                    resultStr.deleteCharAt(resultStr.length() - 1);
                }
            }
        } catch (Exception ignored) {
        }
        return resultStr.toString();
    }

//    public static void main(String[] args) {
//        Object[] arg = new Object[2];
//        System.out.println(Arrays.toString(arg));
//        List<Map> jsonList = JsonHelper.parseToObject(Arrays.toString(args), new TypeReference<>() {
//        });
//        System.out.println(jsonList);
//    }
}