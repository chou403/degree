package com.second.common.util;

import java.io.*;
import java.util.ArrayList;

/**
 * {@code @author} chouchou
 * {@code @date} 2022/11/17
 * {@code @className} 查询路径下的所有统一后缀文件
 * {@code @description} 1
 */
public class SearchSuffixIndir {
    private static final String path = "E:\\workspace\\WEB\\httpserver\\public\\static\\html";

    /**
     *
     * 将给定目录下的及子目录下的 所有的给定后缀名的文件路径打印到一个文件中。
     *
     * 深度遍历该父目录，将符合条件的文件路径存到集合中。（进行过滤）。
     *
     * 再将集合写到文件中。
     *
     *
     */
//    public static void main(String[] args) {
//
//        File dir = new File(path);
//
//        File file = new File(dir,"suffix.txt");
//        //内部类
//        FilenameFilter searchSuffix = new FilenameFilter(){
//            @Override
//            public boolean accept(File dir, String name) {
//                return name.endsWith(".html");
//            }
//        };
//
//        ArrayList<File> path = new ArrayList<File>();
//
//        writeToArr(dir,searchSuffix,path);
//        writeTofile(path,file);
//    }

    private static void writeTofile(ArrayList<File> al, File file) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));

            for(File f: al){
//              bw.write(String.valueOf(f.length()));
                bw.write(f.getAbsolutePath());
                bw.newLine();
                bw.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("写入失败！");
        }finally{
            if(bw != null)
                try {
                    bw.close();
                } catch (IOException e) {
                    throw new RuntimeException("关闭失败！");
                }
        }

    }

    public static void writeToArr(File dir, FilenameFilter searchSuffix, ArrayList<File> al) {

        File []files = dir.listFiles();
        for(File f : files){
            if(f.isDirectory()){
                //递归了。
                writeToArr(f, searchSuffix, al);
            }else{
                if(searchSuffix.accept(dir, f.getName())){
                    System.out.println(f);
                    al.add(f);
                }
            }
        }
    }
}
