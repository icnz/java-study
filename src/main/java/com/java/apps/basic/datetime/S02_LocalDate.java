package com.java.apps.basic.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Java8推出了全新的日期时间API,新API基于ISO标准日历系统，java.time包下的所有类都是不可变类型而且线程安全。
 * <p>
 * 类的名称               描述
 * Instant               时间戳
 * Duration              持续时间、时间差
 * LocalDate             只包含日期，比如：2020-10-01
 * LocalTime             只包含时间，比如：23:12:29
 * LocalDateTime         包含日期和时间，比如：2020-10-01 23:12:29
 * Period                时间段
 * ZoneOffset            时区偏移量，比如：+8:00
 * ZoneDateTime          带时区地时间
 * Clock                 时钟，比如获取目前美国纽约地时间
 * DateTimeFormatter     java.time.format.DateTimeFormatter日期格式化
 */
public class S02_LocalDate {

    public static void main(String[] args) {

        /*----------1、日期----------*/
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();
        System.out.println("今天的日期:" + currentDate);

        // 获取日期的年月日(以当前日期为例)
        int year = currentDate.getYear();
        int month = currentDate.getMonthValue();
        int day = currentDate.getDayOfMonth();
        System.out.println("year:" + year);
        System.out.println("month:" + month);
        System.out.println("day:" + day);

        // 设定特定日期
        LocalDate specificDate = LocalDate.of(2018, 10, 1);
        System.out.println("自定义日期:" + specificDate);

        //日期的增加，plus()方法用来增加天、周、月，ChronoUnit类声明了这些时间单位。
        LocalDate nextWeek = currentDate.plus(1, ChronoUnit.WEEKS);
        System.out.println("一周后的日期为:" + nextWeek);
        //日期的减少，minus()方法用来减少天、周、月，ChronoUnit类声明了这些时间单位
        LocalDate previousYear = currentDate.minus(1, ChronoUnit.YEARS);
        System.out.println("一年前的日期 : " + previousYear);

        // isBefore()和isAfter()用于比较日期前后
        System.out.println("之后的日期:" + currentDate.isAfter(specificDate));
        System.out.println("之后的日期:" + currentDate.isBefore(specificDate));

        // 判断两个日期是否相等
        if (currentDate.equals(specificDate)) {
            System.out.println("时间相等");
        } else {
            System.out.println("时间不等");
        }

        // 判断平年闰年
        if (currentDate.isLeapYear()) {
            System.out.println("今年是闰年");
        } else {
            System.out.println("今年不是闰年");
        }


        /*----------2、时间----------*/
        // 获取当前时间
        LocalTime currentTime = LocalTime.now();
        System.out.println("获取当前的时间,不含有日期:" + currentTime);
        // 设定特定时间
        LocalTime specificTime = LocalTime.of(15, 30);
        System.out.println("设定的时间,不含有日期:" + currentTime);

        // 时间的增加：plusHours、plusMinutes、plusSeconds、plusNanos
        System.out.println("三个小时后的时间为:" + currentTime.plusHours(3));
        // 时间的减少：minusHours、minusMinutes、minusSeconds、minusNanos
        System.out.println("十分钟前的时间为:" + currentTime.minusHours(10));

        /*----------3、日期和时间----------*/
        //获取当前日期和时间
        LocalDateTime currentDateTime = LocalDateTime.now();
        System.out.println("今天的日期和时间 : " + currentDateTime);

        /*----------4、时钟----------*/
        // Returns the current time based on your system clock and set to UTC.
        Clock clock = Clock.systemUTC();
        System.out.println("Clock : " + clock.millis());
        // Returns time based on system clock zone
        Clock defaultClock = Clock.systemDefaultZone();
        System.out.println("Clock : " + defaultClock.millis());


        /*----------5、时区----------*/
        // ZoneId来处理特定时区，ZoneDateTime类来表示某时区下的时间。
        ZoneId america = ZoneId.of("America/New_York");
        LocalDateTime localtDateAndTime = LocalDateTime.now();
        ZonedDateTime dateAndTimeInNewYork = ZonedDateTime.of(localtDateAndTime, america);
        System.out.println("特定时区的当前日期和时间 : " + dateAndTimeInNewYork);

        /*----------6、年月、月日----------*/
        // 月日
        MonthDay birthday = MonthDay.of(specificDate.getMonth(), specificDate.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(currentDate);
        if (currentMonthDay.equals(birthday)) {
            System.out.println("是你的生日");
        } else {
            System.out.println("你的生日还没有到");
        }
        // 年月
        YearMonth currentYearMonth = YearMonth.now();
        System.out.printf("年月中当月的天数 %s: %d%n", currentYearMonth, currentYearMonth.lengthOfMonth());
        YearMonth creditCardExpiry = YearMonth.of(2019, Month.FEBRUARY);
        System.out.printf("信用卡到期日 %s %n", creditCardExpiry);

        /*----------7、时间段----------*/
        // Period
        Period period = Period.between(currentDate, specificDate);
        System.out.println("当前日期和特定日期相差月数 : " + period.getMonths());

        /*----------8、时间戳----------*/
        // 时间戳同时包含了日期和时间，其等同于java.util.Date
        Instant timestamp = Instant.now();
        System.out.println("当前时间戳 " + timestamp.toEpochMilli());

        // Instant转Date
        Date date = Date.from(timestamp);
        // Date转Instant
        Instant instant = date.toInstant();

        /*----------9、日期格式化----------*/
        // 使用预定义的格式化工具解析或格式化日期
        LocalDate formatted = LocalDate.parse("20180205", DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("20180205预定义工具格式化后的日期为:  " + formatted);

        //定义格式化样式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //日期转字符串
        String str = currentDateTime.format(formatter);
        System.out.println("日期转换为字符串:" + str);
        //字符串转日期
        LocalDate date2 = LocalDate.parse(str, formatter);
        System.out.println("日期类型:" + date2);


    }
}
