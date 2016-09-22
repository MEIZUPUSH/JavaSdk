package com.meizu.push.sdk.utils;

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
}
