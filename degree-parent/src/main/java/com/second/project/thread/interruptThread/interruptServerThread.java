package com.second.project.thread.interruptThread;

/**
 * {@code @author}  JSY
 * {@code @date} 2023/8/23
 * {@code @description} interrupt thread
 */
public class interruptServerThread extends Thread {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                // 执行任务
            }
        });
        t.start();
        t.interrupt();
    }
}