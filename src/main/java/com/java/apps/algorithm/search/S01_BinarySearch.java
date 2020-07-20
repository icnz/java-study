package com.java.apps.algorithm.search;

/**
 * 二分查找法
 * 概念：
 * 应用：输入一个有序的元素列表，如果要查找的元素包含在列表中，二分查找返回其位置；否则返回NULL。
 */
public class S01_BinarySearch {

    public static Integer binarySearch(int[] list, int item) {
        int low = 0; //跟踪列表的低位
        int high = list.length - 1; //跟踪列表的高位
        while (low <= high) {
            int mid = (low + high) / 2;
            int guess = list[mid];
            if (guess == item) {
                return mid;
            } else if (guess > item) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] list = {1, 3, 5, 7, 9};
        int item = 3;
        Integer result = binarySearch(list, item);
        System.out.println("二分查找的结果：" + result == null ? "None" : result);
    }
}
