package com.second.main.workbook.completableFuture;

import com.second.common.utils.ThreadTool;

import java.util.concurrent.CompletableFuture;

/**
 * {@code @author} chou401
 * {@code @date} 2023/3/29
 * {@code @className} ApplyAsync_03
 * {@code @description} thenCombine
 * 等待两个任务都执行完，得到两个结果，再把两个加工成一个结果
 * 厨师炒菜和服务员蒸饭同步进行
 */
public class ThenCombine_03 {
    public static void main(String[] args) {
        ThreadTool.print("小白进入餐厅");
        ThreadTool.print("小白点了菜 + 米饭");
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            ThreadTool.print("厨师炒菜");
            ThreadTool.sleep(200);
            return "菜";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            ThreadTool.print("服务员蒸饭");
            ThreadTool.sleep(300);
            return "米饭";
        }), (dish, rice) -> {
            ThreadTool.print("服务员打饭");
            ThreadTool.sleep(100);
            return String.format("%s + %s 好了", dish, rice);
        });

        ThreadTool.print("小白在打游戏");
        ThreadTool.print(String.format("%s,小白开吃", cf.join()));
    }
}
