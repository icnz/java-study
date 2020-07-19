package com.java.apps.feature.optional;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * Optional，起始于Java8，该类提供了一种用于表示可选值而非空引用的类级别解决方案。
 */
public class S01_Optional {
    public static void main(String[] args) {

        /*----------1、创建Optional对象----------*/

        //创建一个空的Optional对象
        Optional<String> emptyOpt = Optional.empty();
        System.out.println(emptyOpt); // Optional.empty

        //创建一个非空的Optional对象，of()方法参数必须非空，否则仍会NullPointerException
        String name = "程序猿";
        Optional<String> opt = Optional.of(name);
        System.out.println(opt); // Optional[程序猿]
        //System.out.println(Optional.of(null)); //java.lang.NullPointerException

        //创建一个既可空又可非空的Optional对象
        // ofNullable()方法内部有一个三元表达式，如果为参数为null，则返回私有常量EMPTY；
        // 否则使用new关键字创建了一个新的Optional对象(不会再抛出NPE异常了)。
        String nullName = null;
        Optional<String> optOrNull = Optional.ofNullable(nullName);
        Optional<String> optOrNull2 = Optional.ofNullable(name);
        System.out.println(optOrNull); // Optional.empty
        System.out.println(optOrNull2); // Optional[程序猿]
        System.out.println();

        /*----------2、判断值是否存在----------*/
        //可以通过isPresent()方法判断一个Optional对象是否存在，
        // 如果存在，该方法返回true，否则返回false(取代了 obj != null 的判断)。
        System.out.println(opt.isPresent()); // true
        System.out.println(optOrNull.isPresent()); // false

        //Java11版本后，可以通过isEmpty()方法判断与isPresent()相反的结果。
        //System.out.println(opt.isEmpty()); // false
        //System.out.println(optOrNull.isEmpty()); // true
        System.out.println();

        /*----------3、非空表达式----------*/
        //ifPresent()，允许使用Lambda表达式执行一些代码，因此，把它称为非空表达式。
        //ifPresent()方法相当于省略了isPresent()为true的判断，缺点是没有相当于else的分支逻辑
        opt.ifPresent(str -> System.out.println(str.length())); // 3
        optOrNull.ifPresent(str -> System.out.println(str.length())); //不执行

        //Java9版本后，可以通过ifPresentOrElse(action, emptyAction)方法执行两种结果，非空时执行action，空时执行emptyAction。
        //opt.ifPresentOrElse(str -> System.out.println(str.length()), () -> System.out.println("为空"));
        //optOrNull.ifPresentOrElse(str -> System.out.println(str.length()), () -> System.out.println("为空"));
        System.out.println();

        /*----------4、获取值----------*/
        //get()方法才用于获取Optional对象值
        // 遗憾的时该方法有缺陷，即当Optional对象的值为null时，该方法会抛出NoSuchElementException异常，与Optional类的初衷相悖。
        // 建议orElseGet()方法获取Optional对象的值。
        String getName = Optional.ofNullable(name).get();
        System.out.println(getName); // 程序媛
        //System.out.println(Optional.ofNullable(nullName).get()); // java.util.NoSuchElementException
        System.out.println();

        /*----------5、设置（获取）默认值----------*/
        //有时候，在创建（获取）Optional对象的时候，需要一个默认值，orElse()和orElseGet()方法就派上用场了。

        //orElse()方法用于返回包裹在Optional对象中的值或orElse()定义的默认值，该方法的参数类型和值的类型一致。
        // 如果ofNullable()不为null，则返回其值,否则返回orElse()默认值。
        // 其缺陷是，无论ofNullable()为null与否，都会走一遍orElse()，效率不高。
        //String elseName = Optional.ofNullable(nullName).orElse("程序媛"); //注释掉以反馈问题
        //String elseName2 = Optional.ofNullable(name).orElse("程序媛"); //注释掉以反馈问题
        String elseName = Optional.ofNullable(nullName).orElse(getDefaultValue());
        System.out.println(elseName); // getDefaultValue \r\n 程序员
        String elseName2 = Optional.ofNullable(name).orElse(getDefaultValue());
        System.out.println(elseName2); // getDefaultValue \r\n 程序猿，由此看出程序走了一遍getDefaultValue()方法

        //orElseGet()用法和orElse()一样，但是其参数是一个Supplier接口的实现(如lambda表达式、::)，且没有orElse()的缺陷
        // 注：`::`是Java8版本后访问类的构造方法，对象方法，静态方法的关键字，用以支持表达式方法，即在表达式中使用类中的方法。
        //String elseGetName = Optional.ofNullable(nullName).orElseGet(() -> "程序媛"); //注释掉以反馈问题
        //String elseGetName2 = Optional.ofNullable(name).orElseGet(() -> "程序媛"); //注释掉以反馈问题
        String elseGetName = Optional.ofNullable(nullName).orElseGet(S01_Optional::getDefaultValue);
        System.out.println(elseGetName); // getDefaultValue \r\n 程序员
        String elseGetName2 = Optional.ofNullable(name).orElseGet(S01_Optional::getDefaultValue);
        System.out.println(elseGetName2); // 程序猿，程序并没有调用getDefaultValue()方法

        //orElseThrow()与orElseGet()类似，只是当值不存在时，抛出supplier接口创建的异常。
        try {
            emptyOpt.orElseThrow(ValueAbsentException::new);
        } catch (Throwable ex) {
            System.out.println(ex.getMessage()); // No value present in the Optional instance
        }
        System.out.println();

        /*----------6、过滤值----------*/
        //filter()方法可以通过传入限定条件(Predicate接口的实现，如lambda表达式)对Optional实例的值进行过滤。
        // 如果条件的结果为true，则返回过滤后的Optional，否则返回一个私有常量EMPTY。
        String password = "123a567e9";
        Optional<String> passwordOpt = Optional.ofNullable(password);
        System.out.println(passwordOpt.filter((value) -> value.length() > 11)); //Optional.empty

        Predicate<String> len6 = pwd -> pwd.length() > 6;
        Predicate<String> len10 = pwd -> pwd.length() < 10;
        System.out.println(passwordOpt.filter(len6.and(len10))); //Optional[123456789]
        System.out.println();

        /*----------7、遍历值----------*/
        //遍历是为了更好地操作
        //如果Optional中有值，map()方法遍历Optional中的值同时执行实现Function接口的函数(如lambda表达式)并得到结果值。
        // 如果结果值不为null则返回包含结果值的Optional，否则返回一个私有常量EMPTY。
        Optional<String> mapedOpt = passwordOpt.map((value) -> value.toUpperCase());
        //或
        //Optional<String> mapedOpt = passwordOpt.map(String::toUpperCase);
        System.out.println(mapedOpt); // Optional[123A567E9]

        //flatMap()与map()类似，区别在于参数不同
        // map方法中的lambda表达式返回值可以是任意类型，在map函数返回之前会包装为Optional。
        // flatMap方法中的lambda表达式返回值必须是Optional实例。
        Optional<String> flatMapedOpt = passwordOpt.flatMap((value) -> Optional.of(value.toUpperCase()));
        System.out.println(flatMapedOpt); // Optional[123A567E9]

    }

    public static String getDefaultValue() {
        System.out.println("getDefaultValue");
        return "程序员";
    }
}

class ValueAbsentException extends Throwable {
    public ValueAbsentException() {
        super();
    }

    public ValueAbsentException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return "No value present in the Optional instance";
    }
}