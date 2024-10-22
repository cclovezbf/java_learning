package com.chenchi.learning.java.bitset.calender;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;

public class CalenderTest {
    static SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void setTimeZone(){
        //es_CU
        TimeZone aDefault = TimeZone.getDefault();
        System.out.println(aDefault);
        System.out.println("默认上海时间="+new Date());
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Chongqing"));
        System.out.println("中国重庆时间="+new Date());
        TimeZone.setDefault(TimeZone.getTimeZone("America/Los_Angeles"));
        System.out.println("美国洛杉矶时间="+new Date());
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        System.out.println("UTC时间="+new Date());
//        TimeZone.setDefault(TimeZone.getTimeZone("UTC+8"));
//        System.out.println("UTC+8时间="+new Date());
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        System.out.println("GMT时间="+new Date());
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+1"));
        System.out.println("GMT+1时间="+new Date());
        TimeZone.setDefault(TimeZone.getTimeZone("EST"));
        System.out.println("EST时间="+new Date());
        TimeZone.setDefault(TimeZone.getTimeZone("CST"));
        System.out.println("CST时间="+new Date());
        TimeZone.setDefault(TimeZone.getTimeZone("CDT"));
        System.out.println("CDT时间="+new Date());
        System.out.println(TimeZone.getTimeZone("CST"));
        System.out.println(TimeZone.getTimeZone("CDT"));
        System.out.println(TimeZone.getTimeZone("UTC"));
        System.out.println(TimeZone.getTimeZone("UTC+1"));
        System.out.println(TimeZone.getTimeZone("UTC+4"));
        System.out.println(TimeZone.getTimeZone("UTC+8"));
    }

    public static void main(String[] args) {

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("CST"), Locale.US);
        calendar.setLenient(false);//容错模式 例如分钟1000会进位到小时
        //2020-03-08 01:02:00
        calendar.set(2020, 2, 8, 1, 2, 0);
        Date date1 = new Date(calendar.getTimeInMillis());
        String format = f1.format(date1);
        System.out.println("设置时间为 2020-03-08 01:02:00," + "CST时区时间为 " + format);
        //2020-03-08 01:02:00 非夏令时间，于北京时间相差14小时。

        //2020-03-08 03:02:00
        calendar.set(2020, 2, 8, 3, 2, 0);
        Date date3 = new Date(calendar.getTimeInMillis());
        String format3 = f1.format(date3);
        System.out.println("设置时间为 2020-03-08 03:02:00," + "CST时区时间为 " + format3);
        //2020-03-08 03:02:00 夏令时间，于北京时间相差13小时。

        //抛出 Exception: HOUR_OF_DAY: 2 -> 3
        //2020-03-08 02:02:00
        calendar.set(2020, 2, 8, 2, 2, 0);
        //抛出异常：java.lang.IllegalArgumentException: HOUR_OF_DAY: 2 -> 3
        Date error = new Date(calendar.getTimeInMillis());
        String format2 = f1.format(error);
        System.out.println("设置时间为 2020-03-08 03:02:00," + "CST时区时间为 " + format2);
    }

    //获取所有时区。
    @Test
    public void getAllTimeZone() {
        String[] ids = TimeZone.getAvailableIDs();
        for (String id : ids)
//            if (id.toLowerCase().contains("asia") || id.toLowerCase().contains("america"))
                System.out.println(id);
    }

    @Test
    public void getAllLocal() {
        Locale[] availableLocales = Locale.getAvailableLocales();
        for (Locale availableLocale : availableLocales) {
            System.out.println(availableLocale.toString());
        }

    }

    @Test
    public void getCal() {
        // 1、取得本地时间：
        Calendar cal = Calendar.getInstance();
// 2、取得时间偏移量：
        int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
        System.out.println("zoneOffset=" + zoneOffset); //此时是 8小时 意味着我是东八区
// 3、取得夏令时差：
        int dstOffset = cal.get(Calendar.DST_OFFSET);
        System.out.println("dstOffset=" + dstOffset); //这里是0 中国无夏令时
// 4、从本地时间里扣除这些差量，即可以取得UTC时间：
        cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        String format = f1.format(new Date(cal.getTimeInMillis()));
        System.out.println("format=" + format); //此时是中时区 也就是格林威治时间
    }

    @Test
    public void getCal2() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-6"), Locale.US);
        calendar.setLenient(false);//容错模式 例如分钟1000会进位到小时
        //2020-03-08 01:02:00
        calendar.set(2020, 2, 8, 1, 2, 0);
        Date date1 = new Date(calendar.getTimeInMillis());
        String format = f1.format(date1);
        System.out.println("设置时间为 2020-03-08 01:02:00," + "CST时区时间为 " + format);
        //2020-03-08 01:02:00 非夏令时间，于北京时间相差14小时。

        //2020-03-08 03:02:00
        calendar.set(2020, 2, 8, 3, 2, 0);
        Date date3 = new Date(calendar.getTimeInMillis());
        String format3 = f1.format(date3);
        System.out.println("设置时间为 2020-03-08 03:02:00," + "CST时区时间为 " + format3);
        //2020-03-08 03:02:00 夏令时间，于北京时间相差13小时。

        //抛出 Exception: HOUR_OF_DAY: 2 -> 3
        //2020-03-08 02:02:00
        calendar.set(2020, 2, 8, 2, 2, 0);
        //抛出异常：java.lang.IllegalArgumentException: HOUR_OF_DAY: 2 -> 3
        Date error = new Date(calendar.getTimeInMillis());
        String format2 = f1.format(error);
        System.out.println("设置时间为 2020-03-08 03:02:00," + "CST时区时间为 " + format2);
    }

    @Test
    public void test() {
        SimpleDateFormat foo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("foo:" + foo.format(new Date()));

        Calendar gc = GregorianCalendar.getInstance();
        System.out.println("gc.getTime():" + gc.getTime());
        System.out.println("gc.getTimeInMillis():" + new Date(gc.getTimeInMillis()));
        Calendar instance = Calendar.getInstance();
// 当前系统默认时区的时间：
        Calendar calendar = new GregorianCalendar();
        System.out.print("时区：" + calendar.getTimeZone().getID() + "  ");
        System.out.println("时间：" + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
// 美国洛杉矶时区
        TimeZone tz = TimeZone.getTimeZone("America/Los_Angeles");
// 时区转换
        calendar.setTimeZone(tz);
        System.out.print("时区：" + calendar.getTimeZone().getID() + "  ");
        System.out.println("时间：" + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));

    }
}
