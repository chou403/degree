package com.second.main.workbook.completableFuture;

import com.second.common.utils.ThreadTool;

import java.util.concurrent.CompletableFuture;

/**
 * {@code @author} chou401
 * {@code @date} 2023/3/29
 * {@code @className} ThenCompose_02
 * {@code @description} thenCompose
 * 方法作用是把前面任务的结果交给下一个异步任务
 * 厨师炒菜 之后服务员打饭
 */
public class ThenCompose_02 {
    public static void main(String[] args) {
        ThreadTool.print("小白进入餐厅");
        ThreadTool.print("小白点了菜 + 米饭");
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            ThreadTool.print("厨师炒菜");
            ThreadTool.sleep(200);
            return "菜";
        }).thenCompose(dish -> CompletableFuture.supplyAsync(() -> {
            ThreadTool.print("服务员打饭");
            ThreadTool.sleep(100);
            return dish + " + 米饭";
        }));

        ThreadTool.print("小白在打游戏");
        // join() 会等待任务执行结束，然后返回任务的结果
        ThreadTool.print(String.format("%s,小白开吃", cf.join()));
    }
}
