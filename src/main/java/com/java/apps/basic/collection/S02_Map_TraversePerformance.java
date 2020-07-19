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
 *
 * 性能测试
 * 　工具：使用 Oracle 官方提供的性能测试工具 JMH（Java Microbenchmark Harness，JAVA 微基准测试套件）
 * 　参考网址：https://github.com/liuhaoeee/JMH-Example
 * 　测试结果：
 * 　Benchmark                                       Mode  Cnt  Score   Error   Units
 * 　S02_Map_TraversePerformance.forEachEntrySet    thrpt    5  1.991 ± 0.724  ops/ms
 * 　S02_Map_TraversePerformance.forEachKeySet      thrpt    5  1.869 ± 1.338  ops/ms
 * 　S02_Map_TraversePerformance.iterateEntrySet    thrpt    5  1.667 ± 1.822  ops/ms
 * 　S02_Map_TraversePerformance.iterateKeySet      thrpt    5  1.970 ± 0.747  ops/ms
 * 　S02_Map_TraversePerformance.lambdaForEach      thrpt    5  2.047 ± 0.660  ops/ms
 * 　S02_Map_TraversePerformance.parallelStreamApi  thrpt    5  1.830 ± 0.736  ops/ms
 * 　S02_Map_TraversePerformance.streamApi          thrpt    5  2.065 ± 0.909  ops/ms
 * 　结果分析：
 * 　Score 列表示平均执行时间， ± 符号表示误差。
 * 　从以上结果可以看出，如果加上后面的误差值的话，可以得出的结论是:
 * 　多线程Stream  > Stream > 迭代器 / ForEach > lambda。
 */

@BenchmarkMode(Mode.Throughput) // 测试类型：吞吐量
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS) // 预热 2 轮，每次 1s
@Measurement(iterations = 5, time = 3, timeUnit = TimeUnit.SECONDS) // 测试 5 轮，每次 3s
@Fork(1) // fork 1 个线程
@State(Scope.Thread) // 每个测试线程一个实例
public class S02_Map_TraversePerformance {

    private static HashMap<Integer, String> map = new HashMap() {{
        // 添加数据
        this.put(1, "Java");
        this.put(2, "Spring");
        this.put(3, "SpringBoot");
        this.put(4, "Mybatis");
        this.put(5, "Redis");
    }};

    public static void main(String[] args) throws RunnerException {
        // 启动基准测试
        Options opt = new OptionsBuilder()
                .include(S02_Map_TraversePerformance.class.getSimpleName()) // 要导入的测试类
                .output("jmh-map-performance.log") // 输出测试结果的文件
                .build();
        new Runner(opt).run(); // 执行测试
    }

    /**
     * 使用迭代器（Iterator）EntrySet 的方式进行遍历
     */
    @Benchmark // 被添加了 @Benchmark 注解的方法都会被测试
    public static void iterateEntrySet() {
        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            System.out.print(entry.getKey());
            System.out.print(entry.getValue());
        }
        System.out.println();
    }

    /**
     * 使用迭代器（Iterator）KeySet 的方式进行遍历
     */
    @Benchmark
    public static void iterateKeySet() {
        Iterator<Integer> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Integer key = iterator.next();
            System.out.print(key);
            System.out.print(map.get(key));
        }
        System.out.println();
    }

    /**
     * 使用 For Each EntrySet 的方式进行遍历
     */
    @Benchmark
    public static void forEachEntrySet() {
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.print(entry.getKey());
            System.out.print(entry.getValue());
        }
        System.out.println();
    }

    /**
     * 使用 For Each KeySet 的方式进行遍历
     */
    @Benchmark
    public static void forEachKeySet() {
        for (Integer key : map.keySet()) {
            System.out.print(key);
            System.out.print(map.get(key));
        }
        System.out.println();
    }

    /**
     * 使用 Lambda 表达式的方式进行遍历
     */
    @Benchmark
    public static void lambdaForEach() {
        map.forEach((key, value) -> {
            System.out.print(key);
            System.out.print(value);
        });
        System.out.println();
    }

    /**
     * 使用 Streams API 单线程的方式进行遍历
     */
    @Benchmark
    public static void streamApi() {
        map.entrySet().stream().forEach((entry) -> {
            System.out.print(entry.getKey());
            System.out.print(entry.getValue());
        });
        System.out.println();
    }

    /**
     * 使用 Streams API 多线程的方式进行遍历
     */
    @Benchmark
    public static void parallelStreamApi() {
        map.entrySet().parallelStream().forEach((entry) -> {
            System.out.print(entry.getKey());
            System.out.print(entry.getValue());
        });
        System.out.println();
    }
}
