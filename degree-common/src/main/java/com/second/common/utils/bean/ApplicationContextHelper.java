package com.second.common.utils.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * ApplicationContextHelper
 * {@code @author}  chouchou
 * {@code @date}    2023/4/12 16:02
 */
@Slf4j
@Component
public class ApplicationContextHelper implements ApplicationContextAware {
    private static ApplicationContext applicationContext = null;

    public ApplicationContextHelper() {
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("执行了Bean 的初始化");
        ApplicationContextHelper.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBeanByName(String name) {
        return applicationContext.getBean(name);
    }

    public static <T> T getBeanByType(Class<T> beanType) {
        return applicationContext.getBean(beanType);
    }

    public static <T> Object getBeanByName(String name, Class<T> beanType) {
        return applicationContext.getBean(name, beanType);
    }

    public static Class getType(String name) {
        return applicationContext.getType(name);
    }

    public static String[] getAliases(String name) {
        return applicationContext.getAliases(name);
    }
}
