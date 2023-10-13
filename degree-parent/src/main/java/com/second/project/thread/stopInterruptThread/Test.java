package com.second.project.thread.stopInterruptThread;

/**
 * {@code @author}  JSY
 * {@code @date} 2023/8/23
 * {@code @description} test
 */
public class Test {
    public static void main(String[] args) {
        StopServerThread serverThread = new StopServerThread();
        serverThread.start();
        serverThread.interrupt();
    }
}
