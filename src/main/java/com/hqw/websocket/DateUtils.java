package com.hqw.websocket;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Description 时间类型的转换
 * @Author qiwen.he
 **/
public class DateUtils {

    public static final String FULL_DATE_STRING_1 = "yyyyMMdd";
    public static final String FULL_DATE_STRING_2 = "yyyy-MM-dd";

    public static final String FULL_DATE_STRING_3 = "yyyy-MM-dd HH:mm";
    public static final String FULL_DATE_STRING_4 = "yyyy-MM-dd HH:mm:ss";
    public static final String FULL_DATE_STRING_5 = "HH:mm:ss";
    public static final String FULL_DATE_STRING_6 = "yyyy年MM月dd日";

    /**
     * 功能描述  字符串转日期
     *
     * @Anthor qiwen.he
     * @date 2019-05-07
     */
    public static Date parse2Date(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 功能描述  日期转字符串
     *
     * @Anthor qiwen.he
     * @date 2019-05-07
     */
    public static String date2String(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 功能描述 拼接时间
     *
     * @Anthor qiwen.he
     * @date 2019-05-07
     */
    public static Date getDate(Date date, String hours) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(FULL_DATE_STRING_2);
        SimpleDateFormat StringFormat = new SimpleDateFormat(FULL_DATE_STRING_4);
        try {
            String time = dateFormat.format(date);
            time = time + " " + hours;
            return StringFormat.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取两个日期之间的日期(包括左右两个日期，具体时间以左边为准)
     *
     * @param start
     * @param end
     * @return
     */
    public static List<Date> getBetweenDates(Date start, Date end) {
        List<Date> result = new ArrayList<Date>();
        Calendar tempStart = Calendar.getInstance();

        result.add(start);
        tempStart.setTime(start);
        tempStart.add(Calendar.DAY_OF_YEAR, 1);

        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        while (tempStart.before(tempEnd)) { //起始时间小于结束时间
            result.add(tempStart.getTime());
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        result.add(end);
        return result;
    }

    /**
     * 时间往后面推count天
     *
     * @param start
     * @param count
     * @return
     */
    public static Date getTimeByCount(Date start, int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        calendar.add(Calendar.DAY_OF_YEAR, count);
        return calendar.getTime();
    }

    /**
     * 判断日期是星期几  星期一到周末：1-7
     *
     * @param date
     * @return
     */
    public static Integer getWeekOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayForWeek = 0;
        if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }


    /**
     * 获得该月第一天
     *
     * @param month
     * @return
     */
    public static Date getFirstDayOfMonth(int month) {
        Calendar cal = Calendar.getInstance();
        // 设置月份
        cal.set(Calendar.MONTH, month - 1);
        // 获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        // 设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        Date time = cal.getTime();
        return time;

//        // 格式化日期
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String firstDayOfMonth = sdf.format(cal.getTime()) + " 00:00:00";
//        return firstDayOfMonth;
    }


    /**
     * 获得该月最后一天
     *
     * @param month
     * @return
     */
    public static Date getLastDayOfMonth(int month) {
        Calendar cal = Calendar.getInstance();
        // 设置月份
        cal.set(Calendar.MONTH, month - 1);
        // 获取某月最大天数
        int lastDay = 0;
        //2月的平年瑞年天数
        if (month == 2) {
            lastDay = cal.getLeastMaximum(Calendar.DAY_OF_MONTH);
        } else {
            lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        // 设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        Date time = cal.getTime();
        return time;

//        // 格式化日期
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String lastDayOfMonth = sdf.format(cal.getTime()) + " 23:59:59";
//        return lastDayOfMonth;
    }

    /**
     * 判断当前时间的的大小
     *
     * @param year
     * @param month
     * @return
     */
    public static Boolean compareDate(int year, int month) {
        Boolean flag = false;
        Calendar cal = Calendar.getInstance();
        int monthOfNow = cal.get(Calendar.MONTH) + 1;
        int yearsOfNow = cal.get(Calendar.YEAR);
        if (year >= yearsOfNow) {
            if (month >= monthOfNow) {
                flag = true;
            }

        }
        return flag;
    }


    /**
     * 判断当前时间是上午还是下午
     *
     * @param date
     * @return
     */
    public static String compareByDate(Date date) {
        SimpleDateFormat StringFormat = new SimpleDateFormat("HH");
        try {
            String time = StringFormat.format(date);
            int num = Integer.parseInt(time);
            if (num <= 12) {
                return "上午";
            } else {
                return "下午";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
