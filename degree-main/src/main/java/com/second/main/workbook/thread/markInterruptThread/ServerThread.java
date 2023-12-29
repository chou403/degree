package com.second.main.workbook.thread.markInterruptThread;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/8/23
 * {@code @description} ServerThread
 */
public class ServerThread extends Thread {
    //volatile修饰符用来保证其它线程读取的总是该变量的最新的值
    public volatile boolean exit = false;
    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8080);
            while (!exit) {
                serverSocket.accept(); //阻塞等待客户端消息
                //do something
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
