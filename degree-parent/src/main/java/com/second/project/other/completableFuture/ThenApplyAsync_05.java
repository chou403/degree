package com.second.project.other.completableFuture;

import com.second.common.util.SmallTool;

import java.util.concurrent.CompletableFuture;

/**
 * {@code @author} JSY
 * {@code @date} 2023/3/29
 * {@code @className} ThenApplyAsync_05
 * {@code @description} thenApplyAsync
 * 把前面异步任务的结果，交给后面的function
 * 两个服务员 分别操作 收款、开发票
 * 若线程一样 （线程复用）
 */
public class ThenApplyAsync_05 {
    public static void main(String[] args) {
        SmallTool.print("小白吃好了");
        SmallTool.print("小白 结账、要求开发票");
        CompletableFuture<String> invoice = CompletableFuture.supplyAsync(() -> {
            SmallTool.print("服务员收款 500元");
            SmallTool.sleep(300);
            return "500";
        }).thenApplyAsync(money -> {
            SmallTool.print(String.format("服务员开发票 面额 %s元", money));
            SmallTool.sleep(200);
            return String.format("%s元发票", money);
        });

        SmallTool.print("小白 街道朋友的电话，想一起打游戏");
        SmallTool.print(String.format("小白拿到%s,准备回家", invoice.join()));
    }
}
