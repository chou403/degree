package com.second.project.thread.stopInterruptThread;

import java.io.FileWriter;

/**
 * {@code @author}  JSY
 * {@code @date} 2023/8/23
 * {@code @description} stop server thread
 */
public class StopServerThread extends Thread{
    @Override
    public void run() {
        try {
            FileWriter fw = new FileWriter("test.txt");
            fw.write("Hello, world!");
            Thread.sleep(1000); //模拟耗时操作
            fw.close(); //关闭文件
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
