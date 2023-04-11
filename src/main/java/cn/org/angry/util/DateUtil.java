package cn.org.angry.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static String defaultFromat;
    private static SimpleDateFormat format;
    static {
        defaultFromat = "yyyy-MM-dd HH:mm:ss";
        format = new SimpleDateFormat(defaultFromat);
    }

    public static String toDateString(Date date){
        format.applyPattern("yyyy-MM-dd");
        return format.format(date);
    }
    public static String toDateTimeString(Date date){
        format.applyPattern(defaultFromat);
        return format.format(date);
    }
    public static Date toDate(String dateStr){
        try {
            format.applyPattern("yyyy-MM-dd");
            return format.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }
    public static Date toDateTime(String dateTimeStr){
        try {
            format.applyPattern(defaultFromat);
            return format.parse(dateTimeStr);
        } catch (ParseException e) {
            return null;
        }
    }

    public static boolean check(String s){
        try {
            format.parse(s);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
