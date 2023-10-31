package com.second.project.conditionalOnProperty;

import com.second.project.springboot.importTest.ConfigB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/9/7
 * {@code @description} conditional property test
 */
@Slf4j
@Component
@EnableScheduling
@ConditionalOnProperty(prefix = "cod", name = "test", havingValue = "111", matchIfMissing = false)
public class ConditionalOnPropertyTest {

    //    @Scheduled(cron = "*/5 * * * * ?")
    public void test() throws Exception {
        log.info("定时器执行。。。。");
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigB.class);
        ConfigB configB = ctx.getBean(ConfigB.class);
        System.out.println(configB.getClass());
    }
}
