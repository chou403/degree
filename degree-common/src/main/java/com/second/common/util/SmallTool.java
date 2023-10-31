package com.second.common.util;

import java.util.StringJoiner;

/**
 * {@code @author} chouchou
 * {@code @date} 2023/3/29
 * {@code @className} SmallTool
 * {@code @description} 多线程小工具
 */
public class SmallTool {
    /**
     * 休眠方法
     * @param millis 毫秒
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印方法
     * @param text 文本
     */
    public static void print(String text) {
        String str = new StringJoiner("\t|\t")
                .add(String.valueOf(System.currentTimeMillis()))
                .add(String.valueOf(Thread.currentThread().getId()))
                .add(Thread.currentThread().getName())
                .add(text)
                .toString();
        System.out.println(str);
    }
}
