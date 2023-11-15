package com.second.project.other.completableFuture;

import com.second.common.utils.ThreadTool;

import java.util.concurrent.CompletableFuture;

/**
 * {@code @author} chouchou
 * {@code @date} 2023/3/29
 * {@code @className} SupplyAsync_01
 * {@code @description} 厨师炒菜 打饭
 * 用来开启异步任务
 */
public class SupplyAsync_01 {
    public static void main(String[] args) {
        ThreadTool.print("小白进入餐厅");
        ThreadTool.print("小白点了菜");
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            ThreadTool.print("厨师炒菜");
            ThreadTool.sleep(200);
            ThreadTool.print("厨师打饭");
            ThreadTool.sleep(100);
            return "菜 做好了";
        });

        ThreadTool.print("小白在打游戏");
        // join() 会等待任务执行结束，然后返回任务的结果
        ThreadTool.print(String.format("%s,小白开吃", cf.join()));
    }
}
