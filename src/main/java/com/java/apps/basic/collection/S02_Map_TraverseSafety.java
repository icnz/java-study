package com.java.apps.basic.collection;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * HashMap 的 7 种遍历方式与性能分析
 * <p>
 * HashMap 遍历从大的方向来说，可分为以下 4 类：
 * 　迭代器（Iterator）方式遍历
 * 　For Each 方式遍历
 * 　Lambda 表达式遍历（JDK 1.8+）
 * 　Streams API 遍历（JDK 1.8+）
 * 但每种类型下又有不同的实现方式，因此具体的遍历方式又可以分为以下 7 种：
 * 　使用迭代器（Iterator）EntrySet 的方式进行遍历
 * 　使用迭代器（Iterator）KeySet 的方式进行遍历
 * 　使用 For Each EntrySet 的方式进行遍历
 * 　使用 For Each KeySet 的方式进行遍历
 * 　使用 Lambda 表达式的方式进行遍历
 * 　使用 Streams API 单线程的方式进行遍历
 * 　使用 Streams API 多线程的方式进行遍历
 * <p>
 * 安全测试结果
 * 　迭代器中循环删除数据安全。
 * 　For循环中删除数据非安全。
 * 　Lambda循环中删除数据非安全。
 * 　Stream循环中删除数据非安全。
 */

public class S02_Map_TraverseSafety {

    private static HashMap<Integer, String> map = new HashMap<>();

    public static void main(String[] args) {
        iterate();
        foreach();
        lambda();
        stream();
    }

    private static void initMap() {
        map.clear();
        map.put(1, "Java");
        map.put(2, "Spring");
        map.put(3, "SpringBoot");
        map.put(4, "Mybatis");
        map.put(5, "Redis");
    }

    /**
     * 使用迭代器进行遍历
     */
    public static void iterate() {
        try {
            //初始化map
            initMap();
            System.out.print("iterate:");
            //遍历
            Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, String> entry = iterator.next();
                if (entry.getKey() == 1) {
                    //删除
                    iterator.remove();
                } else {
                    System.out.print(entry.getKey());
                }
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
    }

    /**
     * 使用 For Each 进行遍历
     */
    public static void foreach() {
        // 循环内删除非安全，错误退出循环
        try {
            // 初始化map
            initMap();
            System.out.print("foreach:");
            // 遍历
            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                if (entry.getKey() == 1) {
                    // 删除
                    map.remove(entry.getKey());
                } else {
                    System.out.print(entry.getKey());
                }
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
    }

    /**
     * 使用 Lambda 表达式进行遍历
     */
    public static void lambda() {
        // 循环内删除非安全，循环不退出
        try { // 加try是为了让程序出错继续执行
            // 初始化map
            initMap();
            System.out.print("lambda-error:");
            // 遍历
            map.forEach((key, value) -> {
                if (key == 1) {
                    // 删除
                    map.remove(key);
                } else {
                    System.out.print(key);
                }
            });
            System.out.println();
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
        // 正确操作，先删除再遍历
        initMap();
        System.out.print("lambda-right:");
        map.keySet().removeIf(key -> key == 1);
        map.forEach((key, value) -> {
            System.out.print(key);
        });
        System.out.println();
    }

    ;

    /**
     * 使用Streams进行遍历
     */
    public static void stream() {
        // 循环内删除非安全，循环不退出
        try { // 加try是为了让程序出错继续执行
            // 初始化map
            initMap();
            System.out.print("stream-error:");
            // 遍历
            map.entrySet().stream().forEach((entry) -> {
                if (entry.getKey() == 1) {
                    //删除
                    map.remove(entry.getKey());
                } else {
                    System.out.print(entry.getKey());
                }
            });
            System.out.println();
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
        // 正确操作，先删除再遍历
        initMap();
        System.out.print("stream-right:");
        map.entrySet().stream().filter(entry -> 1 != entry.getKey()).forEach(entry -> {
            if (entry.getKey() == 1) {
                // 删除
                map.remove(entry.getKey());
            } else {
                System.out.print(entry.getKey());
            }
        });
        System.out.println();
    }
}
