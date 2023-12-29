package com.second.main.workbook.thread.markInterruptThread;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/8/23
 * {@code @description} test
 */
public class Test {
    public static void main(String[] args) {
        ServerThread serverThread = new ServerThread();
        serverThread.start();
        serverThread.exit = true;
    }
}
