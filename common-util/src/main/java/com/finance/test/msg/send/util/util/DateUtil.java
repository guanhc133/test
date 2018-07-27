package com.finance.test.msg.send.util.util;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * <ul>
 * <li>java时间工具类</li>
 * </ul>
 */
public class DateUtil {

    private DateUtil(){
    }
    /**
     * 日期格式:yyyy-MM
     */
    public static final String dateMonthParrern = "yyyy-MM";
    /**
     * 日期格式：yyyyMMdd
     */
    public static final String datePattern = "yyyyMMdd";
    /**
     * 日期格式：yyMMdd
     */
    public static final String shortDatePattern = "yyMMdd";
    /**
     * 日期时间格式：yyyyMMddHHmmss
     */
    public static final String fullPattern = "yyyyMMddHHmmss";
    /**
     * 日期时间格式：yyyyMMddHHmmss
     */
    public static final String readPattern = "yyyy-MM-dd HH:mm:ss,SSS";
    /**
     * 日期时间格式：yyyyMMddHHmmss
     */
    public static final String allPattern = "yyyy-MM-dd HH:mm:ss";
    /**
     * 日期时间格式：yyyy/MM/dd HH:mm:ss
     */
    public static final String ALL_SLASH_PATTERN = "yyyy/MM/dd HH:mm:ss";
    public static final String minutePattern = "yyyy-MM-dd HH:mm";
    /**
     * 日期时间格式：yyyyMM
     */
    public static final String mothPattern = "yyyyMM";

    /**
     * 日期格式：yyyyMMdd
     */
    public static final String monthDayPattern = "MM-dd";

    public static final String monthDayPlainPattern = "MMdd";

    /**
     * 日期时间格式：yyMMddHHmmss
     */
    public static final String partPattern = "yyMMddHHmmss";
    public static final String PATTERN_1 = "yyyy-MM-dd";

    /**
     * 日期时间格式：dd
     */
    public static final String ddPattern = "dd";

    public static final String dPattern = "d";

    public static final String mmPattern = "MM";
    public static final String MPattern = "M";

    /**
     * 格式：HHmmss
     */
    public static final String timePattern = "HHmmss";

    public static final String timeSuffixStart = "000000";

    public static final String timeSuffixEnd = "235959";

    private static final String INVALID_PARAM_MSG = "The payDate could not be null!";

    /**
     * 时间全格式
     */
    public static final String ALL_PATTERN = "yyyyMMddHHmmssSSS";


    public static final String[] DAY_OF_WEEK ={"SUNDAY","MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY"};


    /**
     * 获取当前日期
     *
     * @return 当前日期
     */
    public static Date getCurrentDate() {
        return DateTime.now().toDate();
    }

    /**
     * 获取当前时间 格式： yyyyMMddHHmmss
     *
     * @return 字符日期 格式：yyyyMMddHHmmss
     */
    public static String getCurrent() {
        return getCurrent(fullPattern);
    }

    /**
     * 获取当前时间 格式： 自定义
     *
     * @param pattern 时间格式
     * @return 自定义格式的当前时间
     */
    public static String getCurrent(String pattern) {
        return DateTime.now().toString(pattern);
    }

    /**
     * 将字符串转换成固定格式时间
     *
     * @param date    日期
     * @param pattern 自定义格式
     * @return 转换后日期
     */
    public static Date parse(String date, String pattern) {
        DateTime dateTime = parseTime(date, pattern);
        if (dateTime == null) return null;
        return dateTime.toDate();
    }

    public static DateTime parseTime(String date, String pattern) {
        return DateTimeFormat.forPattern(pattern).parseDateTime(date);
    }


    public static String format(Date date, String pattern) {
        if (date == null) return null;
        return new DateTime(date).toString(pattern);
    }

    public static String convert(String date, String targetPattern) {
        return convert(date, fullPattern, targetPattern);
    }

    public static String convert(String date, String originPattern, String targetPattern) {
        Date originDate = parse(date, originPattern);
        return format(originDate, targetPattern);
    }

    /**
     * 获取当前时间
     *
     * @return Date
     */
    public static Date getCurrentDate(String pattern) {
        DateTimeFormatter format = DateTimeFormat.forPattern(pattern);
        return DateTime.parse(new DateTime().toString(pattern), format).toDate();
    }

    /**
     * 根据 pattern 将 dateTime 时间进行格式化
     * <p/>
     * 用来去除时分秒，具体根据结果以 pattern 为准
     *
     * @param date payDate 时间
     * @return payDate 时间
     */
    public static Date formatToDate(Date date, String pattern) {
        if (date == null) return null;
        DateTimeFormatter format = DateTimeFormat.forPattern(pattern);
        return DateTime.parse(new DateTime(date).toString(pattern), format).toDate();
    }

    /**
     * 日期增减，负数为减
     *
     * @param dayNum 天数
     * @return 时间
     */
    public static Date plusDays(int dayNum) {
        return new DateTime().plusDays(dayNum).toDate();
    }

    /**
     * 日期增减，负数为减
     *
     * @param dayNum 天数
     * @return 时间
     */
    public static Date plusDays(Date date, int dayNum) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, dayNum);
        return calendar.getTime();
    }
    public static Date plushour(Date date, int dayNum) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, dayNum);
        return calendar.getTime();
    }

    /**
     * 日期增减，负数为减
     *
     * @param monthNum 月份
     * @return 时间
     */
    public static Date plusOrAddMonth(Date sourceDate, int monthNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sourceDate);
        calendar.add(Calendar.MONTH, monthNum);
        return calendar.getTime();
    }

    /**
     * 按秒偏移,根据{@code source}得到{@code seconds}秒之后的日期<Br>
     *
     * @param source  , 要求非空
     * @param seconds , 秒数,可以为负
     * @return 新创建的Date对象
     */
    public static Date addSeconds(Date source, int seconds) {
        return addDate(source, Calendar.SECOND, seconds);
    }

    private static Date addDate(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException(INVALID_PARAM_MSG);
        }

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }

    /**
     * 判断两个日期是否在同一天
     *
     * @param day1
     * @param day2
     * @return
     */
    public static boolean isSameDay(Date day1, Date day2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ds1 = sdf.format(day1);
        String ds2 = sdf.format(day2);
        if (ds1.equals(ds2)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断两个日期是否在同月
     *
     * @param day1
     * @param day2
     * @return
     */
    public static boolean isSameMonth(Date day1, Date day2) {
        SimpleDateFormat sdf = new SimpleDateFormat(mothPattern);
        String ds1 = sdf.format(day1);
        String ds2 = sdf.format(day2);
        if (ds1.equals(ds2)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 计算给定的两个日期的差值
     *
     * @param beforeDate
     * @param afterDate
     * @return  afterDate- beforeDate
     */
    public static int compareDate(Date beforeDate, Date afterDate) {
        DateTime before = new DateTime(beforeDate);
        DateTime after = new DateTime(afterDate);
        Days days = Days.daysBetween(before.withTimeAtStartOfDay(), after.withTimeAtStartOfDay());
        return days.getDays();
    }

    /**
     * 计算给定的两个日期相差的月份(忽略天数，例如：3月25减去2月1，是1)
     *  beforeDate - afterDate
     * @param beforeDate
     * @param afterDate
     * @return
     */
    public static int calMonthDiff(Date beforeDate, Date afterDate) {
        Calendar cal1 = new GregorianCalendar();
        cal1.setTime(beforeDate);
        Calendar cal2 = new GregorianCalendar();
        cal2.setTime(afterDate);
        return (cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) * 12 + cal1.get(Calendar.MONTH) - cal2.get(Calendar.MONTH);
    }

    /**
	 * 获取2016年某个月的第一天
	 */
    public static Date getFirstDayOfMonth(int month) {
        Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2016);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
		// calendar.set(year, month, date, hourOfDay, minute, second)
        return calendar.getTime();
    }

    public static int getAgeByBirthday(Date birthday) {
        Calendar cal = Calendar.getInstance();
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(birthday);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                age--;
            }
        }
        return age;
    }

    /**
     * 当前时间是否在{@code dateTime}之前
     *
     * @param dateTime 时间
     * @return 当前时间是否在{@code dateTime}之前
     */
    public static boolean isBefore(Date dateTime) {
        return new DateTime().isBefore(dateTime.getTime());
    }

    /**
     * 当前时间是否在{@code dateTime}之后
     *
     * @param dateTime 时间
     * @return 当前时间是否在{@code dateTime}之前
     */
    public static boolean isAfter(Date dateTime) {
        return new DateTime().isAfter(dateTime.getTime());
    }

    /**
     *  获取给定日期当天最后时间
     * @param time
     * @return
     */
    public static Date getLastTime(Date time){
        if(time==null){
            return null;
        }
        Date date = plusDays(time, 1);
        Date parse = parse(format(date, datePattern), datePattern);
        if(parse==null) {
            return null;
        }
        return new Date(parse.getTime() - 1);
    }

    public static Date getLastHourTime(Date time,int i){
        if(time==null){
            return null;
        }
        Date parse = parse(format(time, datePattern), datePattern);
        if(parse==null) {
            return null;
        }
        return  plushour(new Date(parse.getTime() - 1),i);
    }
    /**
     *  获取给定日期当天最后时间
     * @param time
     * @return
     */
    public static Date getHalfTime(Date time){
        if(time==null){
            return null;
        }
        Date date = plusDays(time, 1);
        Date parse = parse(format(date, datePattern), datePattern);
        if(parse==null) {
            return null;
        }
        return new Date(parse.getTime() - 1);
    }

    /**
     * <b>DESCRIPTION:</b>判断今天是周几<br>
     * <b>CREATE ON:</b>2018/7/6 9:55<br>
     *
     * @return 返回0是星期日、1是星期一、2是星期二、3是星期三、4是星期四、5是星期五、6是星期六
     */
    public static int getDayofweek(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(getCurrentDate());
        return cal.get(Calendar.DAY_OF_WEEK)-1;
    }

    /**
     * <b>DESCRIPTION:</b>判断输入的时间是周几<br>
     * <b>CREATE ON:</b>2018/7/6 9:58<br>
     *
     * @param date 时间
     * @return 返回0是星期日、1是星期一、2是星期二、3是星期三、4是星期四、5是星期五、6是星期六
     */
    public static int getDayofweek(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK)-1;
    }

    /**
     *  获取给定日期当天最后时间
     * @param time
     * @return
     */

}
