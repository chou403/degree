package com.second.project.other.completableFuture;

import com.second.common.util.SmallTool;

import java.util.concurrent.CompletableFuture;

/**
 * {@code @author} chouchou
 * {@code @date} 2023/3/29
 * {@code @className} ThenApply_04
 * {@code @description} thenApply
 * 把前面异步任务的结果，交给后面的function
 * 一个服务员操作收款、开发票
 */
public class ThenApply_04 {
    public static void main(String[] args) {
        SmallTool.print("小白吃好了");
        SmallTool.print("小白 结账、要求开发票");
        CompletableFuture<String> invoice = CompletableFuture.supplyAsync(() -> {
            SmallTool.print("服务员收款 500元");
            SmallTool.sleep(200);
            return "500";
        }).thenApply(money -> {
            SmallTool.print(String.format("服务员开发票 面额 %s元", money));
            SmallTool.sleep(200);
            return String.format("%s元发票", money);
        });

        SmallTool.print("小白 街道朋友的电话，想一起打游戏");
        SmallTool.print(String.format("小白拿到%s,准备回家", invoice.join()));
    }
}
