package com.second.project.other.reentrantLock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.util.concurrent.CompletableFuture;

/**
 * ReentrantLock
 * {@code @author}  chouchou
 * {@code @date}    2023/4/10 16:17
 */
@Slf4j
@RestController
@RequestMapping("/reentrantLockTest")
public class ReentrantLockTest {

//    static ExecutorService executorService = new ThreadPoolExecutor(3, 8,
//            2, TimeUnit.SECONDS, new LinkedBlockingDeque<>(3)
//            , Executors.defaultThreadFactory(),
//            new ThreadPoolExecutor.AbortPolicy());

    @Resource
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @GetMapping
    public void testReentrantLock() {
        CompletableFuture<Void> infoFuture1 = CompletableFuture.runAsync(() -> {
            LOCK.lock();
            drawMoney();
            LOCK.unlock();
        }, threadPoolTaskExecutor);

        CompletableFuture<Void> infoFuture2 = CompletableFuture.runAsync(() -> {
            LOCK.lock();
            drawMoney();
            LOCK.unlock();
        }, threadPoolTaskExecutor);
    }

    private static final TestLock LOCK = new TestLock();

    public static void main(String[] args) {
//        threadPoolTaskExecutor.execute(() -> {
//            LOCK.lock();
//            drawMoney();
//            LOCK.unlock();
//        });
//
//        threadPoolTaskExecutor.execute(() -> {
//            LOCK.lock();
//            drawMoney();
//            LOCK.unlock();
//        });
    }

    public static void drawMoney() {
        log.info("当前线程：{}开始取钱", Thread.currentThread().getName());
        sleep(10);
        log.info("当前线程：{}取完了", Thread.currentThread().getName());
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
