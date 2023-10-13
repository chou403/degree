package com.second.project.other.completableFuture;

import com.second.common.util.SmallTool;

import java.util.concurrent.CompletableFuture;

/**
 * {@code @author} JSY
 * {@code @date} 2023/3/29
 * {@code @className} ApplyToEither
 * {@code @description} applyToEither
 * 上个任务和这个任务一起运行，哪个先运行完成，就把哪个任务结果交给function
 */
public class ApplyToEither_06 {
    public static void main(String[] args) {
        SmallTool.print("张三走出餐厅，来到公交站");
        SmallTool.print("等待 700路 或者 800路 公交到来");
        CompletableFuture<String> bus = CompletableFuture.supplyAsync(() -> {
            SmallTool.print("700路公交正在赶来");
            SmallTool.sleep(300);
            return "700路到了";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            SmallTool.print("800路公交正在赶来");
            SmallTool.sleep(200);
            return "800路到了";
        }), firstComeBus -> firstComeBus);

        SmallTool.print(String.format("%s,小白坐车回家", bus.join()));
    }
}
