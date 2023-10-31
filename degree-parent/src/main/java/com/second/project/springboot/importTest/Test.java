package com.second.project.springboot.importTest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/8/22
 * {@code @description} test a
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigB.class);
        ConfigB configB = ctx.getBean(ConfigB.class);
        ConfigA configA = ctx.getBean(ConfigA.class);
        A a = ctx.getBean(A.class);

        System.out.println(configB.getClass().getSimpleName());
        System.out.println(configA.getClass().getSimpleName());
        System.out.println(a.getClass().getSimpleName());
    }
}
