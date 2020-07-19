package com.java.apps.basic.basicstyle;

public class S02_BitOperator {

    public static void main(String[] args) {

        /*----------1、位运算符----------*/
        //按位与
        int a01 = 12; // 0000 0000 0000 1100
        int b01 = 8; // 0000 0000 0000 1000
        System.out.println(a01 & b01); // 8，0000 0000 0000 1000

        //按位或
        int a02 = 4; // 0000 0000 0000 0100
        int b02 = 8; // 0000 0000 0000 1000
        System.out.println(a01 | b01); // 12，0000 0000 0000 1100

        //按位异或
        int a03 = 31; // 0000 0000 0001 1111
        int b03 = 22; // 0000 0000 0001 0110
        System.out.println(a03 ^ b03); // 9，0000 0000 0000 1001

        //按位取反
        int a04 = 123; // 0000 0000 0111 1011
        System.out.println(~a04); // -124，1111 1111 1000 0100
        System.out.println();

        /*----------2、位移运算符----------*/
        //<<：左移运算符，数的二进制按位向左移，低位补零，高位溢出舍去。每移动一位相当于乘2
        int a05 =1;
        System.out.println(a05 << 1); //2
        System.out.println(a05 << 2); //4
        System.out.println(a05 << 3); //8
        System.out.println();
        short b05 = 9115;
        System.out.println(b05 << 1); //18230
        System.out.println(b05 << 2); //36460
        System.out.println();
    }
}
