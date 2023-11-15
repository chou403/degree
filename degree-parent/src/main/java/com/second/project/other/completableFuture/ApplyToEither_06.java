package com.second.project.other.completableFuture;

import com.second.common.utils.ThreadTool;

import java.util.concurrent.CompletableFuture;

/**
 * {@code @author} chouchou
 * {@code @date} 2023/3/29
 * {@code @className} ApplyToEither
 * {@code @description} applyToEither
 * 上个任务和这个任务一起运行，哪个先运行完成，就把哪个任务结果交给function
 */
public class ApplyToEither_06 {
    public static void main(String[] args) {
        ThreadTool.print("张三走出餐厅，来到公交站");
        ThreadTool.print("等待 700路 或者 800路 公交到来");
        CompletableFuture<String> bus = CompletableFuture.supplyAsync(() -> {
            ThreadTool.print("700路公交正在赶来");
            ThreadTool.sleep(300);
            return "700路到了";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            ThreadTool.print("800路公交正在赶来");
            ThreadTool.sleep(200);
            return "800路到了";
        }), firstComeBus -> firstComeBus);

        ThreadTool.print(String.format("%s,小白坐车回家", bus.join()));
    }
}
