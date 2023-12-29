package com.second.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池
 * {@code @author}  chou401
 * {@code @date}    2023/4/10 18:23
 */
@Slf4j
@EnableAsync
@Configuration
public class ExecutorAspectConfig {

    @Value("${async.executor.thread.core_pool_size}")
    private int corePoolSize;
    @Value("${async.executor.thread.max_pool_size}")
    private int maxPoolSize;
    @Value("${async.executor.thread.queue_capacity}")
    private int queueCapacity;
    @Value("${async.executor.thread.name.prefix}")
    private String namePrefix;

    /**
     * 方法名上使用 @Async("asyncServiceExecutor") 开启子线程方法
     */
    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        log.info("start asyncServiceExecutor");
        ThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
        //线程池创建的核心线程数，线程池维护线程的最少数量，即使没有任务需要执行，也会一直存活
        executor.setCorePoolSize(corePoolSize);
        //最大线程池数量，当线程数>=corePoolSize，且任务队列已满时。线程池会创建新线程来处理任务
        //任务队列已满时, 且当线程数=maxPoolSize，，线程池会拒绝处理任务而抛出异常
        executor.setMaxPoolSize(maxPoolSize);
        //阻塞队列 当核心线程数达到最大时，新任务会放在队列中排队等待执行
        executor.setQueueCapacity(queueCapacity);
        //当线程空闲时间达到keepAliveTime时，线程会退出，直到线程数量=corePoolSize
        //允许线程空闲时间30秒，当maxPoolSize的线程在空闲时间到达的时候销毁
        //如果allowCoreThreadTimeout=true，则会直到线程数量=0
        executor.setKeepAliveSeconds(30);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix(namePrefix);
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean，这样这些异步任务的销毁就会先于Redis线程池的销毁
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住。
        executor.setAwaitTerminationSeconds(60);
        //执行初始化
        executor.initialize();
        return executor;
    }
}
