package com.second.main.workbook.springboot.importTestSelector;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/8/22
 * {@code @description} test
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);

        Config config = ctx.getBean(Config.class);
        ZooConfig zooConfig = ctx.getBean(ZooConfig.class);
        Tiger tiger = ctx.getBean(Tiger.class);

        System.out.println(config.getClass().getSimpleName());
        System.out.println(zooConfig.getClass().getSimpleName());
        System.out.println(tiger.getClass().getSimpleName());
    }
}
