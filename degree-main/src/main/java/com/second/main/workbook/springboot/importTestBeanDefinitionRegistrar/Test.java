package com.second.main.workbook.springboot.importTestBeanDefinitionRegistrar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/8/23
 * {@code @description} test
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);

        Config config = ctx.getBean(Config.class);
        System.out.println(config.getClass());

        Dog dog = ctx.getBean(Dog.class);
        System.out.println(dog.getClass().getSimpleName());
    }
}
