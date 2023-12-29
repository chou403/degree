package com.second.common.utils;

/**
 * {@code @author} chou401
 * {@code @date} 2022/11/17
 * {@code @className} FilterFileUtil
 * {@code @description} 删除无用文件
 */
public class FilterFileUtil {

    private static final String path = "E:\\workspace\\WEB\\httpserver\\public\\static\\html";

//    public static void main(String[] args) throws Exception {
//        String filePath = "E:\\workspace\\WEB\\httpserver\\public\\static\\html\\suffix.txt";
//        List<String> files = Files.readAllLines(Paths.get(filePath),
//                StandardCharsets.UTF_8);
//
//        String menuPath = "E:\\workspace\\WEB\\httpserver\\public\\static\\html\\total.txt";
//        List<String> lines = Files.readAllLines(Paths.get(menuPath),
//                StandardCharsets.UTF_8);
////        List<String> srcList = new ArrayList<>();
////        for (String s : lines) {
////            File temp = new File(s);
////            List<String> tempLines = Files.readAllLines(Paths.get(s),
////                    StandardCharsets.UTF_8);
////            List<String> strings = tempLines.stream().filter(v->v.contains(".html") && v.contains("url")).collect(Collectors.toList());
////            for (String ss : strings) {
////                String sss = ss.substring(0, ss.indexOf(".html"));
////                String str = sss.substring(sss.lastIndexOf("/")+1);
////                String strName = str + ".html";
////                srcList.add(strName);
////            }
////        }
//
//        ArrayList<File> resultList = new ArrayList<>();
//        Map<String, Boolean> map = new HashMap<>();
//        for (String file : files) {
//            map.put(file, true);
//        }
//
//        for (String file : files) {
//            for (String line : lines) {
//                if (file.equals(line)) {
//                    map.put(file, false);
//                }
//            }
//        }
//
//        System.out.println(map);
//
//        for (Map.Entry<String, Boolean> entry:map.entrySet()) {
//            if (entry.getValue()) {
//                File f = new File(entry.getKey());
//                boolean a = f.delete();
//            }
//        }
//
//    }



}
