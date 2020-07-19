package com.java.apps.test.testng;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * TestNG是一个开源自动化测试框架;TestNG表示下一代(Next Generation的首字母)。
 * 其灵感来自JUnit和NUnit，引入了许多新的创新功能，如依赖测试，分组概念，使测试更强大，更容易做到。
 * 旨在涵盖所有类别的测试：单元，功能，端到端，集成等。
 * <p>
 * TestNG的特点：
 * 　注解
 * 　TestNG使用Java和面向对象的功能
 * 　支持综合类测试(例如，默认情况下，不用创建一个新的测试每个测试方法的类的实例)
 * 　独立的编译时测试代码和运行时配置/数据信息
 * 　灵活的运行时配置
 * 　主要介绍“测试组”。当编译测试，只要要求TestNG运行所有的“前端”的测试，或“快”，“慢”，“数据库”等
 * 　支持依赖测试方法，并行测试，负载测试，局部故障
 * 　灵活的插件API
 * 　支持多线程测试
 * <p>
 * 从测试的结果可以看到执行的顺序是beforeTest()-->Test()-->afterTest()，同时Test()方法从dataProvider dp里面接收参数。
 */
//https://www.cnblogs.com/du-hong/default.html?page=7
public class TestNG01 {
    @Test(dataProvider = "dataProvider")
    public void f(Integer n, String s) {
        System.out.println("第一个参数是" + n + ",第二个参数是" + s);
    }

    @DataProvider
    public Object[][] dataProvider() {
        return new Object[][]{
                new Object[]{1, "a"},
                new Object[]{2, "b"}
        };
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("------------开始测试------------");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("------------结束测试------------");
    }
}
