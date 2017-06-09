package com.meizu.push.sdk.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wangxinguo on 2016-8-24.
 */
public class DateUtils {

    public final static String FORMAT_STRING = "yyyy-MM-dd HH:mm:ss";

    public static String date2String(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_STRING);
        return simpleDateFormat.format(date);
    }

    public static String date2String(Date date, String format) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * 字符串日期根据指定格式转换成Date.
     *
     * @param dateString
     * @return
     */
    public static Date str2Date(String dateString) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_STRING);
            return simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException("时间转化格式错误" + "[dateString=" + dateString + "]" + "[FORMAT_STRING=" + FORMAT_STRING + "]");
        }
    }

    /**
     * 字符串日期根据指定格式转换成Date.
     *
     * @param dateString   字符串日期
     * @param formatString 转换格式
     * @return 转换后的Date
     */
    public static Date str2Date(String dateString, String formatString) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatString);
            return simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException("时间转化格式错误" + "[dateString=" + dateString + "]" + "[FORMAT_STRING=" + formatString + "]");
        }
    }
}
