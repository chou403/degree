package com.second.common.util;

import cn.hutool.core.date.DateUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

/**
 * {@code @author} JSY
 * {@code @date} 2023/2/9
 * {@code @className} DateUitls
 * {@code @description} 日期处理 util
 */
public class DateUtils {

    private static final List<String> FMT_LIST = new ArrayList<>() {
        private static final long serialVersionUID = 8959026674904495827L;

        {
            add(BaseDictCode.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
            add(BaseDictCode.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI);
            add(BaseDictCode.DATE_FORMAT_YYYY_MM_DD_HH);
            add(BaseDictCode.DATE_FORMAT_YYYY_MM_DD);
        }
    };

    /***
     * 转换字符串为日期date
     * 自动匹配转化，支持fmtList中的几种格式
     */
    public static Date str2Date(String dateStr, int... fmtIndex) {
        int index = 0;
        if (fmtIndex != null && fmtIndex.length > 0) {
            index = fmtIndex[0];
        }
        if (index > FMT_LIST.size() - 1) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(FMT_LIST.get(index));
        try {
            return format.parse(dateStr);
        } catch (ParseException e1) {
            return str2Date(dateStr, ++index);
        }
    }

    /***
     * date格式化字符串
     */
    public static String date2str(Date date, String fmt) {
        SimpleDateFormat format = new SimpleDateFormat(fmt);
        return format.format(date);
    }

    /**
     * 返回距离当前年月的相差月份
     */
    public static int monthsBetween(String yearMonth) {
        Date data1 = DateUtil.date();
        Date date2 = DateUtil.parse(yearMonth);
        return (int) DateUtil.betweenMonth(data1, date2, true);
    }

    /**
     * 返回当前日期
     */
    public static String getCurrentDateHms() {
        SimpleDateFormat sf = new SimpleDateFormat(BaseDictCode.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
        return sf.format(new Date());
    }

    /**
     * 返回当前日期
     */
    public static String getCurrentDate() {
        SimpleDateFormat sf = new SimpleDateFormat(BaseDictCode.DATE_FORMAT_YYYY_MM_DD);
        return sf.format(new Date());
    }

    /**
     * 返回当前年月
     */
    public static String getCurrentMonth() {
        SimpleDateFormat sf = new SimpleDateFormat(BaseDictCode.DATE_FORMAT_YYYY_MM);
        return sf.format(new Date());
    }

    /**
     * 返回上个月
     */
    public static String getCurrentLastMonth() {
        SimpleDateFormat sf = new SimpleDateFormat(BaseDictCode.DATE_FORMAT_YYYY_MM);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        return sf.format(calendar.getTime());
    }

    /**
     * 获取当月所有天
     */
    public static List<String> getDayListOfMonth() {
        List<String> list = new ArrayList<>();
        SimpleDateFormat sf = new SimpleDateFormat(BaseDictCode.DATE_FORMAT_YYYY_MM_DD);
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.getActualMaximum(Calendar.DATE);
        for (int i = 1; i <= day; i++) {
            String date = year + "-" + month + "-" + i;
            try {
                calendar.setTime(sf.parse(date));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            list.add(sf.format(calendar.getTime()));
        }
        return list;
    }

    /**
     * 根据年月获取当月最后一天
     *
     * @param yearmonth yyyy-MM
     * @return yyyy-MM-dd
     */
    public static String getLastDayOfMonth(String yearmonth) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(BaseDictCode.DATE_FORMAT_YYYY_MM);
            Date dd = format.parse(yearmonth);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dd);
            int cc = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            return yearmonth + "-" + cc;
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 距离当前时间 分钟差
     */
    public static Long getDayToDayOfMinutes(String date) {
        java.time.format.DateTimeFormatter dtf = java.time.format.DateTimeFormatter.ofPattern(BaseDictCode.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI);
        LocalDateTime localDateTime = LocalDateTime.parse(date, dtf);
        Duration duration = Duration.between(LocalDateTime.now(), localDateTime);
        return duration.toMinutes();
    }

    /**
     * 时间戳转日期格式
     */
    public static String getDateOfTimeStamp(Long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat(BaseDictCode.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
        return sdf.format(new Date(timeStamp));
    }

    /**
     * 当前日期加上天数后的日期
     */
    public static String getDateOfAddDay(int num) {
        SimpleDateFormat format = new SimpleDateFormat(BaseDictCode.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, num);
        Date d = ca.getTime();
        return format.format(d);
    }

    /**
     * 2022-03-16 17:34:07.0 -> 2022-03-16 17:34:07
     * 字符转时间
     */
    public static String getStrFormatToStr(String time) {
        DateFormat dateFormat = new SimpleDateFormat(BaseDictCode.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
        String date;
        try {
            date = dateFormat.format(dateFormat.parse(time));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return date;
    }

    /**
     * 获取某个时间，n 年/月/周/日/时/分/秒前或者后的时间
     * n为正时代表时间戳后的某个时间，n为负时代表时间戳前的某个时间，
     */
    public static Date getDateAfterTime(String dateStr, int time, int timeType) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(BaseDictCode.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
            Calendar cal = Calendar.getInstance();
            cal.setTime(df.parse(dateStr));
            cal.add(timeType, time);
            return cal.getTime();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取某个时间，n 年/月/周/日/时/分/秒前或者后的时间
     * n为正时代表时间戳后的某个时间，n为负时代表时间戳前的某个时间，
     */
    public static Date getDateAfterTime(Date date, int time, int timeType) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(timeType, time);
            return cal.getTime();
        } catch (Exception e) {
            return null;
        }
    }
}
