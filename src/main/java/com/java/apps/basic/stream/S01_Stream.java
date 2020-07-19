package com.java.apps.basic.stream;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author DEITY
 */
public class S01_Stream {

    public static void main(String[] args) {

        // 你不鸟我,我也不鸟你
        List<String> list = Arrays.asList("you", "don't", "bird", "me", ",", "I", "don't", "bird", "you");

        List<Integer> a = Arrays.asList(1, 2, 3);
        List<Integer> b = Arrays.asList(4, 5, 6);

        List<BigDecimal> bigDecimal = Arrays.asList(
                new BigDecimal("11.11"),
                new BigDecimal("22.22"),
                new BigDecimal("33.33")
        );

        /***** forEach 循环 *****/
        // 方式一：JDK1.8之前的循环方式
        for (String item : list) {
            System.out.println(item);
        }

        // 方式二：使用Stream的forEach方法
        // void forEach(Consumer<? super T> action)
        list.stream().forEach(item -> System.out.println(item));

        // 方式三：方式二的简化方式
        // 由于方法引用也属于函数式接口，所以方式二Lambda表达式也可以使用方法引用来代替
        // 此种方式就是方式一、方式二的简写形式
        list.stream().forEach(System.out::println);

        /***** filter 过滤 *****/
        // Stream<T> filter(Predicate<? super T> predicate);
        list.stream().filter(item -> item.length() > 3).forEach(System.out::println);

        /***** map 映射 *****/
        //<R> Stream<R> map(Function<? super T, ? extends R> mapper);
        list.stream().map(item -> item.toUpperCase()).forEach(System.out::println);

        /***** of *****/
        List<List<Integer>> ofCollect = Stream.of(a, b).collect(Collectors.toList());
        // [[1, 2, 3], [4, 5, 6]]
        System.out.println(ofCollect);

        /***** flatMap *****/
        // 将多个集合中的元素合并成一个集合
        List<Integer> flatMapList = Stream.of(a, b).flatMap(item -> item.stream()).collect(Collectors.toList());
        // [1, 2, 3, 4, 5, 6]
        System.out.println(flatMapList);

        // 通过Builder模式来构建
        Stream<Object> stream = Stream.builder().add("hello").add("hi").add("byebye").build();

        /***** sorted 排序 *****/
        // Stream<T> sorted(Comparator<? super T> comparator);
        // int compare(T o1, T o2);
        list.stream().sorted((s1, s2) -> s1.compareTo(s2)).forEach(System.out::println);

        /***** distinct 去重复 *****/
        list.stream().distinct().forEach(System.out::println);

        /***** count 总数量 *****/
        System.out.println(stream.count());

        /***** min、max *****/
        Optional<String> optional = list.stream().min((i, j) -> i.compareTo(j));
        String value = optional.get();
        System.out.println(value);

        /***** skip *****/
        // Stream<T> skip(long n)
        list.stream().skip(2).forEach(System.out::println);
        /***** limit *****/
        list.stream().limit(2).forEach(System.out::println);

        /***** collect *****/
        // Stream -> Collection
        List<String> collect = list.stream().collect(Collectors.toList());
        // Stream -> Object[]
        Object[] objects = list.stream().toArray();

        /***** anyMatch、allMatch *****/
        // boolean anyMatch(Predicate<? super T> predicate);
        // parallelStream可以并行计算，速度比stream更快
        System.out.println(list.parallelStream().anyMatch(item -> item.equals("me")));

        /***** reduce 归纳 *****/
        // Optional<T> reduce(BinaryOperator<T> accumulator);
        Optional<String> optional1 = list.stream().reduce((before, after) -> before + "," + after);
        optional1.ifPresent(System.out::println);
        // BigDecimal求和
        System.out.println(bigDecimal.stream().reduce(BigDecimal.ZERO, BigDecimal::add));

        /***** findFirst *****/
        System.out.println(list.stream().findFirst().get());

        /***** findAny *****/
        System.out.println(list.stream().findAny().get());

    }
}
