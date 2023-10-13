package com.second.project.other.completableFuture;

import com.second.common.util.SmallTool;

import java.util.concurrent.CompletableFuture;

/**
 * {@code @author} JSY
 * {@code @date} 2023/3/29
 * {@code @className} ThenCompose_02
 * {@code @description} thenCompose
 * 方法作用是把前面任务的结果交给下一个异步任务
 * 厨师炒菜 之后服务员打饭
 */
public class ThenCompose_02 {
    public static void main(String[] args) {
        SmallTool.print("小白进入餐厅");
        SmallTool.print("小白点了菜 + 米饭");
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            SmallTool.print("厨师炒菜");
            SmallTool.sleep(200);
            return "菜";
        }).thenCompose(dish -> CompletableFuture.supplyAsync(() -> {
            SmallTool.print("服务员打饭");
            SmallTool.sleep(100);
            return dish + " + 米饭";
        }));

        SmallTool.print("小白在打游戏");
        // join() 会等待任务执行结束，然后返回任务的结果
        SmallTool.print(String.format("%s,小白开吃", cf.join()));
    }
}
