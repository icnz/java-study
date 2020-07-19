package com.java.apps.basic.basicstyle;

/***
 * Java四种基本数据类型
 * 1、整数类型
 *    byte 数据类型是8位、有符号的，以二进制补码表示的整数；
 *    short 数据类型是16位、有符号的以二进制补码表示的整数；
 *    int 数据类型是32位、有符号的以二进制补码表示的整数；
 *    long 数据类型是64位、有符号的以二进制补码表示的整数；
 *
 *
 *
 *    类型     内存空间     默认值     取值范围
 *    byte     1字节       0          -2^7 ~ 2^7-1 (-128 ~ 127)
 *    short    2字节       0          -2^15 ~ 2^15-1 (-32768 ~ 32767)
 *    int      4字节       0          -2^31 - 2^31-1 (-21亿 ~ 21亿)
 *    long     8字节       0L         -2^63 ~ 2^63-1
 *
 *    byte类型，虽然存储范围是-128~127，但在计算机中实际存储的是0-255，即正数（0~127）是0~127，负数（-128~-1）是128~255
 *    因为负数在计算机中存储的是他的补码。补码：负数取它的绝对值的二进制数，取反，加1。
 *    例：-128，绝对值是128，二进制是1000 0000，取反是0111 1111，加1是1000 0000（128）
 *       -1，绝对值是1，二进制是0000 0001，取反是1111 1110，加1是1111 1111（255）
 *
 *    bytes[i] & 0xFF，目的是保留第八位的不变性，即byte类型数据的正确性。
 *    byte与其他类型转换，计算机会自动将byte转化为对应类型的位数再运算，计算机自动将高位补1.
 *    例：-12转int，二进制8位是 1111 0011，32位是 1111 1111 1111 1111 1111 1111 1111 0100
 *       0xFF的32位二进制是 0000 0000 0000 0000 0000 0000 1111 1111
 *       bytes[i] & 0xFF的结果是 0000 0000 0000 0000  0000 0000  1111 0100
 *
 * 2、浮点类型
 *    float 数据类型是单精度、32位、符合IEEE 754标准的浮点数；
 *    double 数据类型是双精度、64 位、符合IEEE 754标准的浮点数；
 *    注：float和double不能用来表示精确的值，如货币；
 *
 *    类型     内存空间     默认值
 *    float    4字节       0.0f
 *    double   8字节       0.0d
 *
 * 3、字符类型
 *    char 类型是一个单一的16位Unicode字符；
 *
 *    类型     内存空间     默认值     取值范围
 *    char     2字节       0          0 ~ 65535
 *
 * 4、布尔类型
 *    boolean数据类型表示一位的信息；
 *
 *    类型     内存空间     默认值
 *    boolean  未定义       false
 *    注：boolean类型的位数与其所代表的数据类型有关
 */
public class S01_BasicStyle {

    public static void main(String[] args) {

        /*----------1-1、整数类型----------*/
        //byte类型
        byte a01 = 12;
        byte b01 = 12, c01 = -13;
        System.out.println("基本类型：byte 二进制位数：" + Byte.SIZE);
        System.out.println("包装类：java.lang.Byte");
        System.out.println("最小值：Byte.MIN_VALUE=" + Byte.MIN_VALUE);
        System.out.println("最大值：Byte.MAX_VALUE=" + Byte.MAX_VALUE);
        System.out.println();

        //short类型
        short a02 = 156;
        short b02 = 156, c02 = -231;
        System.out.println("基本类型：short 二进制位数：" + Short.SIZE);
        System.out.println("包装类：java.lang.Short");
        System.out.println("最小值：Short.MIN_VALUE=" + Short.MIN_VALUE);
        System.out.println("最大值：Short.MAX_VALUE=" + Short.MAX_VALUE);
        System.out.println();

        //int类型(整数的默认数据类型)
        int a03 = 3456;
        int b03 = 3456, c03 = -5678;
        int d03 = b03 + c03;
        System.out.println("基本类型：int 二进制位数：" + Integer.SIZE);
        System.out.println("包装类：java.lang.Integer");
        System.out.println("最小值：Integer.MIN_VALUE=" + Integer.MIN_VALUE);
        System.out.println("最大值：Integer.MAX_VALUE=" + Integer.MAX_VALUE);
        System.out.println();

        //long类型，声明时需要在整数后面加L或l后缀
        long a04 = 9876543210L;
        long b04 = 123L, c04 = -456L;
        long d04 = 123L + 456L;
        System.out.println("基本类型：long 二进制位数：" + Long.SIZE);
        System.out.println("包装类：java.lang.Long");
        System.out.println("最小值：Long.MIN_VALUE=" + Long.MIN_VALUE);
        System.out.println("最大值：Long.MAX_VALUE=" + Long.MAX_VALUE);
        System.out.println();
        //错误示例
        long a05 = 123456789 * 987654321;
        System.out.println(a05); //-67153019
        System.out.println();

        /*----------1-2、整数不同进制----------*/
        //十进制
        int a06 = 33;

        //八进制(以0开头的数字)
        int b06 = 033;

        //十六进制(以0x或0X开头的数字)
        int c06 = 0x33;

        //二进制(以0b或0B开头的数字)
        int d06 = 0b10101; //

        System.out.println(a06); //33
        System.out.println(b06); //27(八进制的十进制表示)
        System.out.println(c06); //51（十六进制的十进制表示）
        System.out.println(d06); //21（二进制的十进制表示）
        System.out.println();

        long e06 = 25L;
        long f06 = 031L;
        long g06 = 0x19L;
        long h06 = 0b11001L;
        System.out.println(e06); //25
        System.out.println(f06); //25(八进制的十进制表示)
        System.out.println(g06); //25（十六进制的十进制表示）
        System.out.println(h06); //25（二进制的十进制表示）
        System.out.println();




        /*----------2-1、浮点数据类型----------*/
        //float类型，声明时需要在整数后面加F或f后缀
        float a07 = 1.1F;
        float b07 = 1.1F, c07 = 2.4f;
        System.out.println("基本类型：float 二进制位数：" + Float.SIZE);
        System.out.println("包装类：java.lang.Float");
        System.out.println("最小值：Float.MIN_VALUE=" + Float.MIN_VALUE);
        System.out.println("最大值：Float.MAX_VALUE=" + Float.MAX_VALUE);
        System.out.println();

        //double类型(浮点数的默认数据类型)，声明时可在整数后面加D或d后缀也可不加
        double a08 = 3.1415926;
        double b08 = 1.1D, c08 = 2.4d;
        System.out.println("基本类型：double 二进制位数：" + Double.SIZE);
        System.out.println("包装类：java.lang.Double");
        System.out.println("最小值：Double.MIN_VALUE=" + Double.MIN_VALUE);
        System.out.println("最大值：Double.MAX_VALUE=" + Double.MAX_VALUE);
        System.out.println();

        /*----------2-2、浮点数不精准解决法----------*/
        //四舍五入法
        double a09 = 4.35 * 100;
        System.out.println(a09); //434.99999999999994
        System.out.println(Math.round(a09)); //435
        System.out.println();
        //最小数对比法（使用Math.abs()方法与最小值1e-6比较）
        //1e-6是一个理论的最小数，如果某个数比1e-6还要小，则可以认为此数为0。
        double a10 = 0.1;
        double b10 = 2.0 - 1.9;
        System.out.println(a10); //0.1
        System.out.println(b10); //0.10000000000000009
        System.out.println(a10 == b10); //false
        System.out.println(Math.abs(a10 - b10) < 1e-6); //true
        System.out.println();

        /*----------3-1、字符数据类型----------*/
        //字符：用单引号包含的可打印的单个符号，比如：'a','B','9','@','人',' '
        //字符类型赋值方式一：字符字面值
        char a11 = '8';
        char b11 = '汉', c11 = 'a';
        System.out.println(a11); //8，此8为字符字面值
        System.out.println(b11); //汉
        System.out.println(c11); //a
        System.out.println(a11 == 8); //false
        //Java中字符的运算其实是它字符编码的运算
        System.out.println(a11 * 1); //56，此56为字符'8'的字符编码
        System.out.println();
        //字符类型赋值方式二：字符编码
        char a12 = 56;
        char b12 = 27721, c12 = 97;
        System.out.println(a12); //8，此8为字符字面值
        System.out.println(b12); //汉
        System.out.println(c12); //a
        System.out.println(a11 == a12); //true
        System.out.println();
        System.out.println("基本类型：char 二进制位数：" + Character.SIZE);
        System.out.println("包装类：java.lang.Character");
        // 以数值形式而不是字符形式将Character.MIN_VALUE输出到控制台
        System.out.println("最小值：Character.MIN_VALUE="
                + (int) Character.MIN_VALUE); //0
        // 以数值形式而不是字符形式将Character.MAX_VALUE输出到控制台
        System.out.println("最大值：Character.MAX_VALUE="
                + (int) Character.MAX_VALUE); //65535
        System.out.println();
        //字符类型赋值方式三：Unicode转义序列
        //Unicode转义序列以\U开始，后面跟四个十六进制数字，范围从0000到FFFF（即0~65535）
        char a13 = '\u0061';
        System.out.println(a13); //a
        System.out.println("[\u0000]"); //[ ]
        System.out.println("[\uFFFF]"); //[￿]
        System.out.println();
        //字符类型赋值方式四：八进制转义序列
        //八进制转义序列以\开始，后面跟三个八进制数字，范围从000到377（即0~255）
        char a14 = '\141';
        System.out.println(a14); //a
        System.out.println("[\000]"); //[ ]
        System.out.println("[\377]"); //[ÿ]
        System.out.println();

        /*----------3-2、转义字符----------*/
        //[]为了看到显示效果
        System.out.println("[\']"); //单引号字符
        System.out.println("[\"]"); //双引号字符
        System.out.println("[\\]"); //反斜杠字符
        System.out.println("[\t]"); //垂直制表符，将光标移到下一个制表符的位置
        System.out.println("[\r]"); //回车
        System.out.println("[\n]"); //换行
        System.out.println("[\b]"); //退格
        System.out.println("[\f]"); //换页
        System.out.println("[\101]"); //八进制表示的字符A
        System.out.println();

        /*----------4、布尔数据类型----------*/
        boolean a = true;
        boolean b = false;

        /*----------5、下划线数字字面量----------*/
        //Java8开始，允许整数使用下划线，使得数字更容易阅读，尤其大数字时。
        int x1 = 2_014;
        int x2 = 2___014;
        int x3 = 02_014;
        int x4 = 0b0111_1011_0001;
        int x5 = 0x7_B_1;
        byte x6 = 1_2_7;
        double x7 = 2_014.01_11;
        System.out.println(x1); // 2014
        System.out.println(x2); // 2014，多个下划线
        System.out.println(x3); // 1036，八进制数十进制表示
        System.out.println(x4); // 1969，二进制数十进制表示
        System.out.println(x5); // 1969，十六进制数十进制表示
        System.out.println(x6); // 127
        System.out.println(x7); // 2_014.01_11
    }
}
