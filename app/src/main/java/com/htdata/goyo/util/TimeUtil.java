package com.htdata.goyo.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * cuibo
 * 2018/5/8 11:24
 */

public class TimeUtil {

    /**
     * func:通过具体日期来获得星期几（中式）
     *
     * @param date 标准日期
     * @return 星期几
     */
    public static String getChineseWeekDay(String date) {
        String weekTime = "星期";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(format.parse(date));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                weekTime += "日";
                break;
            case 2:
                weekTime += "一";
                break;
            case 3:
                weekTime += "二";
                break;
            case 4:
                weekTime += "三";
                break;
            case 5:
                weekTime += "四";
                break;
            case 6:
                weekTime += "五";
                break;
            case 7:
                weekTime += "六";
                break;
            default:
                throw new IllegalArgumentException("Illegal date format");
        }
        return weekTime;

    }

    /**
     * 获取当前时间24小时制
     *
     * @param format (yyyy-MM-dd hh:mm:ss && MM-dd hh:mm:ss)
     * @return
     */
    public static String getCurrtTime(String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        String date = dateFormat.format(new Date());
        return date;
    }

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的字符串（秒）
     * @param format
     * @return
     */
    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty())
            format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return dateFormat.format(new Date(Long.valueOf(seconds + "000")));

    }

    /**
     * @param ms     毫秒
     * @param format
     * @return
     */
    public static String timeStamp2DateMS(String ms, String format) {
        if (ms == null || ms.isEmpty() || ms.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty())
            format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return dateFormat.format(new Date(Long.valueOf(ms)));

    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param date_str 字符串日期
     * @param format   如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
            return String.valueOf(dateFormat.parse(date_str).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 通过Date转换时间格式
     *
     * @param date
     * @param formatStr 如：yyyy-MM-dd HH:mm:ss;yyyy-MM-dd;
     * @return
     */
    public static String getTime(Date date, String formatStr) {//可根据需要自行截取数据显示
        LogUtils.i("getTime() = " + "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(date);
    }


    /**
     * 获取 最近天  日期  近7天或者更多 返回集合
     */
    public static List<String> getLatelyDayTimeList(int latelyDay) {
        List<String> dates = new ArrayList<String>();
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        for (int i = 0; i < latelyDay; i++) {
            String mYear = String.valueOf(c.get(Calendar.YEAR));// 获取当前年份
            String mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
            String mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH) - i);// 获取当前日份的日期号码
            String date = mYear + "-" + mMonth + "-" + mDay;
            LogUtils.v("======获取今天往后一周的日期========" + date);
            dates.add(date);
        }
        return dates;
    }

    /**
     * 获取 最近天  日期  近7天或者更多
     */
    public static String getLatelyDayTime(int latelyDay) {
//        int day = latelyDay - 1 ;
//        String dates = "" ;
//        final Calendar c = Calendar.getInstance();
//        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
//        String mYear = String.valueOf(c.get(Calendar.YEAR));// 获取当前年份
//        String mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
//        String mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH) - day);// 获取当前日份的日期号码
//        if ("-1".equals(mDay)){
//            mDay = "1" ;
//        }
//        dates = mYear + "-" + mMonth + "-" + mDay;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat fromat = new SimpleDateFormat("yyyy-MM-dd");
        calendar.add(Calendar.DATE, -(latelyDay - 1));
        String days_ago = fromat.format(calendar.getTime());
//        LogUtils.v("======最近往前天========" + days_ago);
        return days_ago;
    }

    /**
     * 获取 最几个月  日期  近一个月或者更多
     */
    public static String getLatelyMonthTime(int latelyMonth) {
        int month = latelyMonth - 1;
        String dates = "";
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String mYear = String.valueOf(c.get(Calendar.YEAR));// 获取当前年份
        String mMonth = String.valueOf(c.get(Calendar.MONTH) + 1 - month);// 获取当前月份
        String mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前日份的日期号码
        dates = mYear + "-" + mMonth + "-" + mDay;
//        LogUtils.v("======最近往前月========" + dates);
        return dates;
    }

    /**
     * 获取 最几个年  日期  近一年或者更多
     */
    public static String getLatelyYearhTime(int latelYear) {
        int year = latelYear - 1;
        String dates = "";
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String mYear = String.valueOf(c.get(Calendar.YEAR) - latelYear);// 获取当前年份
        String mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        String mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前日份的日期号码
        dates = mYear + "-" + mMonth + "-" + mDay;
//        LogUtils.v("======最近往前年========" + dates);
        return dates;
    }

    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014年06月14日16时09分00秒"）
     *
     * @param time
     * @return
     */
    public static String toTimeData(String time) {
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long l = Long.valueOf(time);
        timeString = sdf.format(new Date(l));//单位秒
        return timeString;

    }

    public static String toTimeData2(String time) {
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        long l = Long.valueOf(time);
        timeString = sdf.format(new Date(l));//单位秒
        return timeString;

    }


    /**
     * 将时间转换为时间戳
     * @param time
     * @return
     * @throws ParseException
     */
    public String dateToStamp(String time) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(time);
        long ts = date.getTime();
//        LogUtils.v("========将时间转换为时间戳========"+ts);
        return String.valueOf(ts);
    }

    public static String getMonthDay(String data){
        String newDateStr = null;
        try {
            Date formatedDate = new SimpleDateFormat("yyyy-MM-dd").parse(data);
            newDateStr = new SimpleDateFormat("MM-dd").format(formatedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDateStr;
    }



    /**
     * 比较两个日期的大小，日期格式为yyyy-MM-dd
     *
     * @param str1 the first date
     * @param str2 the second date
     * @return true <br/>false
     */
    public static boolean isDateOneBigger(String str1, String str2) {
        boolean isBigger = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt1 = null;
        Date dt2 = null;
        try {
            dt1 = sdf.parse(str1);
            dt2 = sdf.parse(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (dt1.getTime() > dt2.getTime()) {
            isBigger = true;
        } else if (dt1.getTime() < dt2.getTime()) {
            isBigger = false;
        }
        return isBigger;
    }

    /**
     * 获取两个日期的天数
     *
     * @return
     */
    public static long getDayCount(String startTime, String endTime) {
        long count = 0;
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = new GregorianCalendar();
            Date d1 = df.parse(endTime);
            Date d2 = df.parse(startTime);
            count = (d1.getTime() - d2.getTime()) / (60 * 60 * 1000 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return count;
    }


}
