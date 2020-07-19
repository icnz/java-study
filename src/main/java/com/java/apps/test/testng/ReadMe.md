**TestNG**是一个开源自动化测试框架;TestNG表示下一代(Next Generation的首字母)。  
其灵感来自JUnit和NUnit，引入了许多新的创新功能，如依赖测试，分组概念，使测试更强大，更容易做到。  
旨在涵盖所有类别的测试：单元，功能，端到端，集成等。  

**TestNG的特点：**  
　注解  
　TestNG使用Java和面向对象的功能  
　支持综合类测试(例如，默认情况下，不用创建一个新的测试每个测试方法的类的实例)  
　独立的编译时测试代码和运行时配置/数据信息   
　灵活的运行时配置  
　主要介绍“测试组”。当编译测试，只要要求TestNG运行所有的“前端”的测试，或“快”，“慢”，“数据库”等  
　支持依赖测试方法，并行测试，负载测试，局部故障  
　灵活的插件API  
　支持多线程测试  

从测试的结果可以看到执行的顺序是beforeTest()-->Test()-->afterTest()，同时Test()方法从dataProvider dp里面接收参数。 

**一个测试通常分为三步：**  
1.编写测试业务逻辑，并且在代码中插入TestNG annotations。  
2.在 testng.xml 或 build.xml 添加你的测试信息。例如类名，希望运行的组等等。  
3.运行TestNG。  

**testNG测试文档中的概念：**  
1.一套测试（suite）由一个XML文件所表示。它能够包含一个或者多个测试，<suite> 标记来定义。  
2.test由<test>标记来表示一个测试，并且可以包含一个或者多个TestNG类。  
3.TestNG 类是包含至少一个TestNG annotation的java类，由<class>标签描述并包含一个或多个测试方法。  
4.测试方法，就是一个普通的Java方法，在由@Test标记。  

**IDEA配置TestNG**  
构建项目-->编辑配置...-->Templetes-->TestNG
输出目录：${SOURCEPATH}/../../test/java/${PACKAGE}/${FILENAME}  