package com.second.main.workbook.completableFuture;

import com.second.common.utils.ThreadTool;

import java.util.concurrent.CompletableFuture;

/**
 * {@code @author} chou401
 * {@code @date} 2023/3/29
 * {@code @className} Exceptionally_07
 * {@code @description} exceptionally
 * 处理异常情况 链路上面的任何一个任务 抛出异常 都会调用
 */
public class Exceptionally_07 {
    public static void main(String[] args) {
        ThreadTool.print("张三走出餐厅，来到公交站");
        ThreadTool.print("等待 700路 或者 800路 公交到来");
        CompletableFuture<String> bus = CompletableFuture.supplyAsync(() -> {
            ThreadTool.print("700路公交正在赶来");
            ThreadTool.sleep(100);
            return "700路到了";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            ThreadTool.print("800路公交正在赶来");
            ThreadTool.sleep(200);
            return "800路到了";
        }), firstComeBus -> {
            ThreadTool.print(firstComeBus);
            if (firstComeBus.startsWith("700")) {
                throw new RuntimeException("撞树了。。。");
            }
            return firstComeBus;
        }).exceptionally(e -> {
            ThreadTool.print(e.getMessage());
            ThreadTool.print("小白叫出租车");
            return "出租车 叫到了";
        });

        ThreadTool.print(String.format("%s,小白坐车回家", bus.join()));
    }
}
