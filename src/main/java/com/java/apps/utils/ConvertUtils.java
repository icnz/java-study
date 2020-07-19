package com.java.apps.utils;

import java.util.Arrays;

/**
 * 数据转换工具类
 * https://github.com/FantasyLWX/BlogDemo/blob/master/app/src/main/java/com/fantasy/blogdemo/utils/ConvertUtils.java
 */
public class ConvertUtils {

    public static final int BYTE = 1;
    public static final int KB = 1024;
    public static final int MB = 1048576;
    public static final int GB = 1073741824;

    public static final int MSEC = 1;
    public static final int SEC = 1000;
    public static final int MIN = 60000;
    public static final int HOUR = 3600000;
    public static final int DAY = 86400000;

    // @IntDef({BYTE, KB, MB, GB})
    // @Retention(RetentionPolicy.SOURCE)
    // @interface memoryUnit {
    // }
    //
    // @IntDef({MSEC, SEC, MIN, HOUR, DAY})
    // @Retention(RetentionPolicy.SOURCE)
    // @interface timeUnit {
    // }

    private ConvertUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F'};

    public static void main(String[] args) throws Exception {
        String str = "hello";
        byte[] bytes1 = str.getBytes("UTF-8");
        System.out.println(Arrays.toString(bytes1));
        String bits = bytesToBits(bytes1);
        System.out.println(bits);
        byte[] bytes2 = bitsToBytes(bits);
        System.out.println(Arrays.toString(bytes2));
    }

    /**
     * 字节数组转字符串形式的二进制
     *
     * @param bytes 字节数组
     * @return
     */
    public static String bytesToBits(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        // 循环字节数组
        for (byte byt : bytes) {
            // 将byt的二进制转换为二进制字符串（方便展示）
            for (int i = 7; i >= 0; --i) {
                // (byt >> i) & 0x01，将byt的二进制对应的i位移动到0位再与二进制1（00000001）做按位与运算
                // == 0 ? '0' : '1'，判断是0还是1，再以字符串形式还原
                sb.append(((byt >> i) & 0x01) == 0 ? '0' : '1');
            }
        }
        return sb.toString();
    }


    /**
     * 字符串形式的二进制转字节数组
     *
     * @param bits 二进制字符串
     * @return
     */
    public static byte[] bitsToBytes(String bits) {
        int lenMod = bits.length() % 8;
        int byteLen = bits.length() / 8;
        // 如果bits不为8的倍数就前位补0直至补到位
        if (lenMod != 0) {
            StringBuilder bitsBuilder = new StringBuilder(bits);
            for (int i = lenMod; i < 8; i++) {
                bitsBuilder.insert(0, "0");
            }
            bits = bitsBuilder.toString();
            byteLen++;
        }
        byte[] bytes = new byte[byteLen];
        for (int i = 0; i < byteLen; ++i) {
            for (int j = 0; j < 8; ++j) {
                bytes[i] <<= 1;
                bytes[i] |= bits.charAt(i * 8 + j) - '0';
            }
        }
        return bytes;
    }
}
