/**
 * Copyright (C), 上海布鲁爱电子商务有限公司
 */
package personal.louchen.fastapi.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Date 工具类
 *
 * @author Eric.Lou
 * @version $Id: CodeGenerator.java, v 0.1 2015-07-07 上午10:44
 */
public class DateUtil {
    private static final Logger log                     = LoggerFactory.getLogger(DateUtil.class);

    public static final String  DEFAULT_DATE_FORMAT     = "yyyy-MM-dd";

    public static final String  DEFAULT_TIME_FORMAT     = "HH:mm:ss";

    public static final String  DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private final static SimpleDateFormat getFormat(String pattern, Locale locale) {
        return new SimpleDateFormat(pattern, locale);
    }

    /**
     * format 日期类型 格式化成字符串类型
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        return format(date, pattern, Locale.getDefault());
    }

    /**
     * format 日期类型 格式化成字符串类型
     *
     * @param date
     * @param pattern
     * @param locale
     * @return
     */
    public static String format(Date date, String pattern, Locale locale) {
        if(date==null){
            return "";
        }
        if (StringUtils.isNotBlank(pattern)) {
            String format = getFormat(pattern, locale).format(date);
            return format;
        }
        throw new IllegalArgumentException("param pattern can not be null");
    }

    /**
     * parse 字符串类型转成日期类型
     *
     * @param dateString
     * @param pattern
     * @return
     */
    public static Date parse(String dateString, String pattern) {
        return parse(dateString, pattern, Locale.getDefault());
    }

    /**
     * 字符串类型转成日期类型
     *
     * @param dateString
     * @param pattern
     * @param locale
     * @return
     */
    public static Date parse(String dateString, String pattern, Locale locale) {
        if (StringUtils.isNotBlank(dateString)) {
            ParsePosition parsePosition = new ParsePosition(0);
            Date date = getFormat(pattern, locale).parse(dateString, parsePosition);
            Object[] objects = { dateString, pattern };
            log.debug("dateString:[{}], pattern:[{}], parsePosition:[{}]", objects);
            return date;
        }
        throw new IllegalArgumentException("param dateString can not be null");
    }

    /**
     * 增加或减少指定数量的时间
     *
     * @param date   时间
     * @param field  计算域
     * @param amount 数值
     */
    public static Date add(Date date, int field, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    /**
     * 增加或减少指定天数
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addDay(Date date, int amount) {
        return add(date, Calendar.DAY_OF_YEAR, amount);
    }

    /**
     * 增加或减少指定分钟
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addMinutes(Date date, int amount) {
        return add(date, Calendar.MINUTE, amount);
    }

    /**
     * 获得指定时间当天 00:00:00 的Date对象
     *
     * @param date 指定时间
     * @return 结果
     */
    public static Date dayStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获得指定时间当天 23:59:59 的Date对象
     *
     * @param date 指定时间
     * @return 结果
     */
    public static Date dayEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 获得中文周N
     *
     * @return 结果
     */
    public static String weekDayInChinese() {
        return weekDayInChinese(new Date());
    }

    /**
     * 根据指定日期获得中文周N
     *
     * @param date 指定日期
     * @return 结果
     */
    public static String weekDayInChinese(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(GregorianCalendar.DAY_OF_WEEK);
        switch (week) {
            case 1:
                return "日";
            case 2:
                return "一";
            case 3:
                return "二";
            case 4:
                return "三";
            case 5:
                return "四";
            case 6:
                return "五";
            case 7:
                return "六";
            default:
                return "";
        }
    }

    /**
     *  获取当前系统时间
     * @return
     */
    public static Date getNow() {
        return new Date();
    }
}
