package com.second.main.workbook.test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * {@code @author}  chou401
 * {@code @date} 2023/11/17
 * {@code @description} lambda 常用表达式
 */
public class TestLambda {
    public static void main(String[] args) {

        List<String> list = Arrays.asList("apple", "banana", "orange");

        // 打印
        for (String s : list) {
            System.out.println(s);
        }

        list.forEach(System.out::println);

        // 排序
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        Collections.sort(list, ((o1, o2) -> o1.compareTo(o2)));

        // 过滤
        List<String> list2 = new ArrayList<>();
        for (String s : list) {
            if (s.startsWith("a")) {
                list2.add(s);
            }
        }

        List<String> list3 = list.stream().filter(s -> s.startsWith("a")).collect(Collectors.toList());

        // 获取数据长度
        List<Integer> list4 = new ArrayList<>();
        for (String s : list) {
            list4.add(s.length());
        }

        List<Integer> list5 = list.stream().map(String::length).collect(Collectors.toList());

        // 归约
        List<Integer> list6 = Arrays.asList(1, 2, 3, 4, 5);
        int sum = 0;
        for (Integer i : list6) {
            sum += i;
        }

        int sum1 = list6.stream().reduce(0, Integer::sum);

        // 分组
        Map<Integer, List<String>> groups = new HashMap<>();
        for (String s : list) {
            int length = s.length();
            if (!groups.containsKey(length)) {
                groups.put(length, new ArrayList<>());
            }
            groups.get(length).add(s);
        }

        Map<Integer, List<String>> groups2 = list.stream().collect(Collectors.groupingBy(String::length));

        // 线程调用
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        });

        Thread thread1 = new Thread(() -> System.out.println("hello world"));

        // 字符转大写
        String str = "hello world";
        if (str != null) {
            System.out.println(str.toUpperCase());
        }

        Optional.ofNullable(str).map(String::toUpperCase).ifPresent(System.out::println);

        // 流水线操作
        List<String> list7 = new ArrayList<>();
        for (String s : list) {
            if (s.startsWith("a")) {
                list7.add(s.toUpperCase());
            }
        }
        Collections.sort(list7);

        List<String> list8 = list.stream().filter(s -> s.startsWith("a")).map(String::toUpperCase).sorted().collect(Collectors.toList());

    }
}
