package com.second.project.springboot.fullImport;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/8/22
 * {@code @description} test
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext ctx =  new AnnotationConfigApplicationContext(MyConfigurationConfig.class);

        MyConfigurationConfig myConfigurationConfig = ctx.getBean(MyConfigurationConfig.class);
        A a = ctx.getBean(A.class);
        B b = ctx.getBean(B.class);
        System.out.println(myConfigurationConfig);
        System.out.println(a);
        System.out.println(b);
    }
}
