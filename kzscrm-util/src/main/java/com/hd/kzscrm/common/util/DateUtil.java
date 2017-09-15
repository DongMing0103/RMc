package com.hd.kzscrm.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日期转换函数
 *
 * @author W 2011-7-24
 */
public class DateUtil {

    public static final String Formater_yyyy_MM_dd = "yyyy-MM-dd";
    public static final String Formater_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static final String Formater_yyyyMMddHHmmss = "yyyyMMddHHmmss";

    public static final String Formater_MM_dd = "MM-dd";


    /**
     * 期货需要的日前格式
     *
     * @param cal
     * @return
     */
    public static String getShowDate(Calendar cal) {
        if (cal == null) {
            return "";
        }
        int year = cal.get(Calendar.YEAR);
        int mouth = cal.get(Calendar.MONTH) + 1;
        String mouthStr = mouth > 9 ? mouth + "" : "0" + mouth;
        String yearStr = (year + "").substring(2);
        return yearStr + mouthStr;
    }
    
    /**
     * 根据指定时间，获取指定日期后的某一天（年，月，日，时，分，秒）
     * @author 黄霄仪
     * @date 2017年4月24日 下午2:52:02
     * @param assignTime 指定时间
     * @param calendarField calendar的枚举字段
     * @param calendarAmount 增加时间的权重
     */
    public static Date getDateByAssignTime(Date assignTime,int calendarField, int calendarAmount){
    	Calendar calendar=Calendar.getInstance();
    	calendar.setTime(assignTime);
    	calendar.add(calendarField, calendarAmount);
    	return calendar.getTime();
    }

    /**
     * 公共的显示时间
     */
    public static String formateDateString(Date date) {
        if (null == date) {
            return "";
        }
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        if (now.getTime() - date.getTime() >= 1) {
//            if (date.getTime() + 24 * 60 * 60 * 1000 < now.getTime()) {
            if (date.getTime() < calendar.getTimeInMillis()) { // 昨天
                return dateToString(date, "MM-dd");
            } else if (date.getTime() + 60 * 60 * 1000 < now.getTime()) {
                return dateToString(date, "HH:mm");
            } else if (date.getTime() + 60 * 1000 < now.getTime()) {
                return (now.getTime() - date.getTime()) / (60 * 1000) + "分钟前";
            } else {
                return (now.getTime() - date.getTime()) / 1000 + "秒前";
            }
        } else if (now.getTime() - date.getTime() < 0) { //未来时间，显示前一天的月-天
            Date yesterday = new Date(now.getTime() - 24 * 60 * 60 * 1000);
            return dateToString(yesterday, "MM-dd");
        }

        return dateToString(date, "MM-dd");
    }
    
    /**
     * 公共的显示时间
     * 当天 显示 时-分
     * 超过显示  月-日
     */
    public static String formateDateStringOneDay(Date date) {
        if (null == date) {
            return "";
        }
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        if (now.getTime() - date.getTime() >= 1) {
//            if (date.getTime() + 24 * 60 * 60 * 1000 < now.getTime()) {
            if (date.getTime() < calendar.getTimeInMillis()) { // 昨天
                return dateToString(date, "MM-dd");
            } else {//当天
                return dateToString(date, "HH:mm");
            }
        } else if (now.getTime() - date.getTime() < 0) { //未来时间，显示前一天的月-天
            Date yesterday = new Date(now.getTime() - 24 * 60 * 60 * 1000);
            return dateToString(yesterday, "MM-dd");
        }

        return dateToString(date, "MM-dd");
    }

    //公共显示时间  年-月-日
    public static String formateDateLongString(Date date) {
        if (null == date) {
            return "";
        }
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        if (now.getTime() - date.getTime() >= 1) {
//            if (date.getTime() + 24 * 60 * 60 * 1000 < now.getTime()) {
            if (date.getTime() < calendar.getTimeInMillis()) { // 昨天
                return dateToString(date, "yyyy-MM-dd");
            } else if (date.getTime() + 60 * 60 * 1000 < now.getTime()) {
                return dateToString(date, "HH:mm");
            } else if (date.getTime() + 60 * 1000 < now.getTime()) {
                return (now.getTime() - date.getTime()) / (60 * 1000) + "分钟前";
            } else {
                return (now.getTime() - date.getTime()) / 1000 + "秒前";
            }
        } else if (now.getTime() - date.getTime() < 0) { //未来时间，显示前一天的月-天
            Date yesterday = new Date(now.getTime() - 24 * 60 * 60 * 1000);
            return dateToString(yesterday, "yyyy-MM-dd");
        }

        return dateToString(date, "yyyy-MM-dd");
    }
 
   
    
    /**
     * 公共的显示时间
     */
    public static String formateDateStringLanuage(Date date, String lan) {
        if (null == date) {
            return "";
        }
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        if (now.getTime() - date.getTime() >= 1) {
            if (date.getTime() < calendar.getTimeInMillis()) { // 昨天
                return dateToString(date, "MM-dd");
            } else if (date.getTime() + 60 * 60 * 1000 < now.getTime()) {
                return dateToString(date, "HH:mm");
            } else if (date.getTime() + 60 * 1000 < now.getTime()) {
                if ("en".equals(lan)) {
                    return (now.getTime() - date.getTime()) / (60 * 1000) + "minutes ago";
                }
                if ("span".equals(lan)) {
                    return (now.getTime() - date.getTime()) / (60 * 1000) + "Antes de X minutos";
                }
                return (now.getTime() - date.getTime()) / (60 * 1000) + "分钟前";
            } else {
                if ("en".equals(lan)) {
                    return (now.getTime() - date.getTime()) / 1000 + "seconds ago";
                }
                if ("span".equals(lan)) {
                    return (now.getTime() - date.getTime()) / 1000 + "Antes de X segundos";
                }
                return (now.getTime() - date.getTime()) / 1000 + "秒前";
            }
        } else if (now.getTime() - date.getTime() < 0) { //未来时间，显示前一天的月-天
            Date yesterday = new Date(now.getTime() - 24 * 60 * 60 * 1000);
            return dateToString(yesterday, "MM-dd");
        }
        return dateToString(date, "MM-dd");

    }

    /**
     * 时间显示
     */
    public static String formateTime(Date date) {
        if (null == date) {
            return "";
        }
        Date now = new Date();
        if (now.getTime() - date.getTime() >= 1) {
            return dateToString(date, Formater_yyyy_MM_dd_HH_mm_ss);
        } else if (now.getTime() - date.getTime() < 0) { //未来时间，显示前一天的月-天
            Date yesterday = new Date(now.getTime() - 24 * 60 * 60 * 1000);
            return dateToString(yesterday, Formater_yyyy_MM_dd_HH_mm_ss);
        }
        if (date.getTime() + 24 * 60 * 60 * 1000 < now.getTime()) {
            return dateToString(date, Formater_yyyy_MM_dd_HH_mm_ss);
        } else if (date.getTime() + 60 * 60 * 1000 < now.getTime()) {
            return dateToString(date, "HH:mm");
        } else if (date.getTime() + 60 * 1000 < now.getTime()) {
            return (now.getTime() - date.getTime()) / (60 * 1000) + "分钟前";
        } else {
            return (now.getTime() - date.getTime()) / 1000 + "秒前";
        }
    }

    /**
     * 交货时间
     */
    public static String formateJHDateString(Date date) {
        if (null == date) {
            return "";
        }
        return dateToString(date, "MM-dd");
    }

    /**
     * 回料交货时间
     */
    public static String formateHLDateString(Date date) {
        if (null == date) {
            return "";
        }
        if (date.getTime() >= (new Date().getTime())) {
            return dateToString(new Date(), "MM-dd");
        }
        return dateToString(date, "MM-dd");
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isNull(String str) {
        str = str != null ? str.trim() : str;
        return str == null || "".equals(str) ? true : false;
    }

    /**
     * 获取当前的时间戳
     *
     * @return
     */
    public static String getCurrentTimestamp() {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date today = new Date();
        return formater.format(today);
    }

    /**
     * 获取当前的日期
     *
     * @return
     */
    public static String getNowDate() {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        return formater.format(today);
    }

    /**
     * Date 对象转换成对应格式的 字符串
     *
     * @param date
     * @param format 如："yyyy-MM-dd HH:mm:ss"，默认："yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static String dateToString(Date date, String format) {
        if (date == null)
            return "";
        SimpleDateFormat formater = new SimpleDateFormat(isNull(format) ? "yyyy-MM-dd HH:mm:ss" : format.trim());
        return formater.format(date);
    }

    public static String dateToString2(Date date, String format) {
        if (date == null)
            return "";
        Date now = new Date();
        SimpleDateFormat formater = new SimpleDateFormat(isNull(format) ? "yyyy-MM-dd HH:mm:ss" : format.trim());
        if (now.getTime() - date.getTime() < 0) { //未来时间，显示前一天的月-天
            //Date yesterday = new Date(now.getTime() - 24 * 60 * 60 * 1000);
            return formater.format(now);
        }
        return formater.format(date);
    }

    public static String dateStyle2ToString(Date date) {
        if (date == null)
            return "";
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return formater.format(date);
    }

    public static String dateToShortStr(Date date, String format) {
        if (date == null)
            return "";
        SimpleDateFormat formater = new SimpleDateFormat(isNull(format) ? "yyyy-MM-dd" : format.trim());
        return formater.format(date);
    }

    /**
     * Date 对象转换成对应格式的 字符串
     *
     * @param date 默认："yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static String dateToString(Date date) {
        if (date == null)
            return "";
        return dateToString(date, null);
    }

    /**
     * Date 对象转换成对应格式的 字符串
     *
     * @param dateSource
     * @param format     如："yyyy-MM-dd HH:mm:ss"，默认："yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static Date timeStrToDate(String dateSource, String format) {
        if (isNull(dateSource))
            return null;
        SimpleDateFormat formater = new SimpleDateFormat(isNull(format) ? "yyyy-MM-dd HH:mm:ss" : format.trim());
        try {
            return formater.parse(dateSource);
        } catch (ParseException e) {
            System.out.println("hello lsf");
            e.printStackTrace();
            return null;
        }
    }

    public static Date toStrToDateYMD(String enddate) {
        return timeStrToDate(enddate, "yyyy-MM-dd");
    }

    /**
     * Date 对象转换成对应格式的 字符串
     *
     * @param dateSource 默认："yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static Date timeStrToDate(String dateSource) {
        if (isNull(dateSource))
            return null;
        return timeStrToDate(dateSource, null);
    }

    /**
     * 得到系统日期
     *
     * @return
     */
    public static String getDate() {
        Calendar calendar = Calendar.getInstance();
        String year = calendar.get(Calendar.YEAR) + "";
        String month = calendar.get(Calendar.MONTH) + 1 + "";
        String day = calendar.get(Calendar.DAY_OF_MONTH) + "";
        if (month.length() == 1)
            month = "0" + month;

        return year + "-" + month + "-" + day;
    }

    /**
     * 得到系统日期,xx月xx日 xx xx:xx
     *
     * @return
     */
    public static String getWapDate() {
        Calendar calendar = Calendar.getInstance();
        String month = calendar.get(Calendar.MONTH) + 1 + "";
        String day = calendar.get(Calendar.DAY_OF_MONTH) + "";
        String hour = calendar.get(Calendar.HOUR_OF_DAY) + "";
        String minute = calendar.get(Calendar.MINUTE) + "";

        if (month.length() == 1)
            month = "0" + month;

        return month + "月" + day + "日 " + hour + ":" + minute;
    }

    public static Date lastDayOfMonth2(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, value);
        return cal.getTime();
    }

    /**
     * 取得当月最后一天
     *
     * @param date
     * @return
     */
    public static String getLastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, value);
        return dateToString(cal.getTime(), "dd");
    }

    /**
     * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
     *
     * @param nowdate
     * @param delay
     * @return
     */
    public static String getNextDay(String nowdate, int delay) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String mdate = "";
            Date d = timeStrToDate(nowdate);
            long myTime = (d.getTime() / 1000) + delay * 24 * 60 * 60;
            d.setTime(myTime * 1000);
            mdate = format.format(d);
            return mdate;
        } catch (Exception e) {
            return "";
        }
    }

    public static Date getNextDayDate(Date date, int delay) {
        try {
            Date d = new Date(date.getTime());
            long myTime = (d.getTime() / 1000) + delay * 24 * 60 * 60;
            d.setTime(myTime * 1000);

            return d;
        } catch (Exception e) {
            return new Date();
        }
    }

    /**
     * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
     *
     * @param nowdate
     * @param delay      小于 0，过去多小天，大于0 未来多小天
     * @param dateFormat
     * @return
     */
    public static String getNextDay(String nowdate, int delay, String dateFormat) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String mdate = "";
            Date d = timeStrToDate(nowdate, dateFormat);
            long myTime = (d.getTime() / 1000) + delay * 24 * 60 * 60;
            d.setTime(myTime * 1000);
            mdate = format.format(d);
            return mdate;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
     *
     * @param nowdate
     * @param delay
     * @param returnDateFormat 自定义返回数据的格式
     * @param dateFormat
     * @return
     */
    public static String getPreOrNextDay(String nowdate, int delay, String returnDateFormat, String dateFormat) {
        try {
            if (isNull(returnDateFormat)) {
                returnDateFormat = "yyyy-MM-dd";
            }
            SimpleDateFormat format = new SimpleDateFormat(returnDateFormat);
            String mdate = "";
            Date d = timeStrToDate(nowdate, dateFormat);
            long myTime = (d.getTime() / 1000) + delay * 24 * 60 * 60;
            d.setTime(myTime * 1000);
            mdate = format.format(d);
            return mdate;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 返回今天的时间段
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String[] getTodayPeriods() {
        String today = dateToString(new Date(), Formater_yyyy_MM_dd);
        return new String[]{today + " 00:00:00", today + " 23:59:59"};
    }

    /**
     * 返回昨天的时间段
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String[] getYesterdayPeriods() {
        String today = dateToString(new Date(), Formater_yyyy_MM_dd);
        String beforeDay = getNextDay(today, -1, Formater_yyyy_MM_dd);
        return new String[]{beforeDay + " 00:00:00", beforeDay + " 23:59:59"};
    }

    /**
     * 返回一个星期(7天前)的时间段
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String[] getWeekPeriods() {
        String today = dateToString(new Date(), Formater_yyyy_MM_dd);
        String beforeDay = getNextDay(today, -7, Formater_yyyy_MM_dd);
        return new String[]{beforeDay + " 00:00:00", today + " 23:59:59"};
    }

    /**
     * 返回的一个月时间段
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String[] getMonthDayPeriods(int difMonths) {
        difMonths = difMonths == 0 ? 1 : difMonths;
        String today = dateToString(new Date(), Formater_yyyy_MM_dd);
        String beforeDay = getNextDay(today, -7 * 4 * difMonths, Formater_yyyy_MM_dd);
        return new String[]{beforeDay + " 00:00:00", today + " 23:59:59"};
    }

    /**
     * 得到系统当前的时间
     *
     * @return
     */
    public static String getSysDateTime() {
        return dateToString(new Date(), DateUtil.Formater_yyyy_MM_dd_HH_mm_ss);
    }

    public static String getSysDateTime(String formatter) {
        if (formatter == null || formatter.trim().length() == 0) {
            formatter = DateUtil.Formater_yyyy_MM_dd_HH_mm_ss;
        }
        return dateToString(new Date(), formatter);
    }

    /**
     * 将calendar的星期几转化为我们习惯的（1-星期一，7-星期日）
     *
     * @param dayOfWeek
     * @return
     */
    public static long toChineseWeek(long dayOfWeek) {
        return dayOfWeek - 1 == 0 ? 7 : dayOfWeek - 1;
    }

    /**
     * 将当前时间转化为星期几 （1-星期一，7-星期日）
     */
    public static long toChineseWeekForNow() {
        long dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        return toChineseWeek(dayOfWeek);
    }

    /**
     * 将1-7 转换为一到日
     */
    public static String weekToChinese() {
        long d = toChineseWeekForNow();
        if (d == 1) {
            return "一";
        }
        if (d == 2) {
            return "二";
        }
        if (d == 3) {
            return "三";
        }
        if (d == 4) {
            return "四";
        }
        if (d == 5) {
            return "五";
        }
        if (d == 6) {
            return "六";
        }
        if (d == 7) {
            return "日";
        }
        return "";
    }

    /**
     * 验证字符串是否是合法的日期
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static boolean isValidDate(String dateStr, String format) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            dateFormat.setLenient(false);
            dateFormat.parse(dateStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 返回当月的起始 日期 2011-01-01 : 2011-01-31
     *
     * @return
     */
    @SuppressWarnings("deprecation")
	public static String[] getMonthPeriods(int difMonth) {
        Date today = new Date();
        int year = today.getYear() + 1900;
        int month = today.getMonth() + difMonth + 1;
        Calendar c = new GregorianCalendar(today.getYear(), today.getMonth() + difMonth, today.getDate());
        // System.out.println(year+" "+month+"  "+c.getActualMaximum(Calendar.DATE));
        return new String[]{year + "-" + month + "-01", year + "-" + month + "-" + c.getActualMaximum(Calendar.DATE)};
    }

    /**
     * 检测日期的格是不是 yyyy-MM-dd HH:mm:ss格式的
     *
     * @param
     * @return true:格式正确 false:格式错误
     */
    public static boolean checkDateFormat(String dateStr) {
        Pattern pattern = Pattern
                .compile("^(((20[0-3][0-9]-(0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|(20[0-3][0-9]-(0[2469]|11)-(0[1-9]|[12][0-9]|30))) (20|21|22|23|[0-1][0-9]):[0-5][0-9]:[0-5][0-9])$");
        Matcher m = pattern.matcher(dateStr);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 检测日期的格式
     *
     * @param type 1:yyyy-MM-dd HH:mm:ss 2:yyyy-MM-dd 3:yyyy-MM-dd HH:mm
     * @return true:格式正确 false:格式错误
     */
    public static boolean checkDateFormat(String dateStr, int type) {
        String patternStr = "";
        if (type == 1) {
            checkDateFormat(dateStr);
        } else if (type == 2) {
            patternStr = "^((20[0-3][0-9]-(0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|(20[0-3][0-9]-(0[2469]|11)-(0[1-9]|[12][0-9]|30)))$";
        } else if (type == 3) {
            patternStr = "^(((20[0-3][0-9]-(0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|(20[0-3][0-9]-(0[2469]|11)-(0[1-9]|[12][0-9]|30))) (20|21|22|23|[0-1][0-9]):[0-5][0-9])$";
        }
        Pattern pattern = Pattern.compile(patternStr);
        Matcher m = pattern.matcher(dateStr);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @description 多个字符串 转换为数字相加
     * @author GZL
     * @date 2015-1-20
     */
    public static long stringPlus(String... s) {
        try {
            long sum = 0;
            if (s != null && s.length > 0) {
                for (int i = 0; i < s.length; i++) {
                    if (s[i].equals("")) {
                        return 0;
                    }
                    sum += Long.parseLong(s[i].trim());
                }
                return sum;
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return 0;
    }

    /**
     * 取得合约当月 如yyMMcode
     * <pre>
     * 关于电解铜当月合约的切换时间
     * 切换时间是每月15日，如果15号是礼拜六等休息日就到工作日换。在电解铜网站期货合约显示方面，
     * 比如1.15日还是显示1501的数据，16日就显示1502，若15日是周六，则继续显示1501的数据，直到下周一才显示1502数据。
     * </pre>
     * <p/>
     * 1501
     *
     * @return
     */
    public static String getCurrentMainFuturesCode() {

        int yy = Integer.parseInt(DateUtil.getSysDateTime("yy"));
        int weekDay = DateUtils.getDayOfWeek();
        int iCurrentMM = CommUtil.parseInt(DateUtil.getSysDateTime("MM"));
        int dd = CommUtil.parseInt(DateUtil.getSysDateTime("dd"));
        // 周六日，尚没开市，仍取星期五,日期月的行情
        if (dd >= 16) {
            if (weekDay < 6) {
                iCurrentMM = iCurrentMM + 1;
            }
        }
        if (iCurrentMM > 12) {
            // 取新的一年1月份
            yy = yy + 1;
            iCurrentMM = 1;
        }

        String strYyyyMM = yy + (iCurrentMM < 10 ? "0" + iCurrentMM : iCurrentMM + "");
        return strYyyyMM;
    }

    /**
     * 星期六或日有特殊处理，取得当天0点的时间
     *
     * @return
     */
    public static Date getNowTradeZeroDate() {
        try {
            String zeroDate = DateUtils.getFormatDate("yyyy-MM-dd") + " 00:00:00";
            Date zeroDay = DateUtils.timeStrToDate(zeroDate, "yyyy-MM-dd HH:mm:ss");
            return zeroDay;
        } catch (Exception e) {
            return new Date();
        }
    }

    

    /***
     * @return 如2015-07-01 00:00:00,2015-07-01 23:59:59
     * @author LX
     * <p/>
     * 返回每天的零点到24点的时间段
     */
    public static String getCurrentDate() {
        String gt = DateUtils.getFormatDate("yyyy-MM-dd") + " 00:00:00";
        String lt = DateUtils.getFormatDate("yyyy-MM-dd") + " 23:59:59";
        return gt + "," + lt;
    }

    /**
     * 获取到明天的秒数
     *
     * @return
     */
    public static long getSecondsToNextDay() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        Calendar now = Calendar.getInstance();
        return (c.getTimeInMillis() - now.getTimeInMillis()) / 1000;
    }

    public static Date getMaxTime() {
        return DateUtil.toStrToDateYMD("2099-12-31");
    }

    /**
     * 随机时间在8:30-18：00之间
     */
    @SuppressWarnings("deprecation")
	public static String randomTime() {
        Date dateTime = new Date();
        int hours = dateTime.getHours();
        int minutes = dateTime.getMinutes();
        int seconds = dateTime.getSeconds();
        if (hours < 8 || hours >= 18) {
            hours = random(8, 18) <= 9 ? '0' + random(8, 18) : random(8, 18);
            minutes = (random(0, 59) <= 9 ? '0' + random(0, 59) : random(0, 59));
            seconds = (random(0, 59) <= 9 ? '0' + random(0, 59) : random(0, 59));

            if (hours == 8 && minutes < 30) {
                minutes = random(30, 59);
            }

        }
        hours = hours <= 9 ? '0' + hours : hours;
        minutes = minutes <= 9 ? '0' + minutes : minutes;
        seconds = seconds <= 9 ? '0' + seconds : seconds;
        return hours + "" + minutes + "" + seconds;
    }

    public static int random(int min, int max) {
        Random random = new Random();

        return random.nextInt(max) % (max - min) + min;
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * @param dateString
     * @return
     * @XSD
     */
    public static boolean isDateDeadLine(String dateString) {
        Date dateDeadLine = timeStrToDate(dateString);
        Date now = new Date();
        if (dateDeadLine.getTime() - now.getTime() > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 处理发布时间为00:00:00为当前时间
     * @param date
     * @return 
     * 
     * */
    public static Date psCreateTime(Date date){
    	String ymd = dateToString(date, "yyyy-MM-dd");
        String time = dateToString(date, "HH:mm:ss");
        if(time!="" && time.equals("00:00:00") ){
        	time = DateUtil.dateToString(new Date(), "HH:mm:ss");
        	date = timeStrToDate(ymd + " " + time);
        }
    	return date;
    }
    
    /**
     * 是否大于一天
     *
     * @param date 时间
     * @return 
     * @throws Exception
     */
    public static boolean moreOneDay(Date date) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = new Date();
        date = sdf.parse(sdf.format(date));
        newDate = sdf.parse(sdf.format(newDate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        long time1 = cal.getTimeInMillis();
        cal.setTime(newDate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        if(between_days >= 1){
        	return true;
        }else{
        	return false;
        }
    }
    /**
     * yyyyMMdd转换成yyyy-MM-dd
     * @param date
     * @return
     */
    public static String dateFormat(String date){
    	String newDate="";
    	try {
    		if(date!=null&&!date.equals("")&&date.length()==8){
    			SimpleDateFormat fmt=new SimpleDateFormat("yyyyMMdd");
            	SimpleDateFormat fmt2=new SimpleDateFormat("yyyy-MM-dd");
            	Date dateFmt=fmt.parse(date);
            	newDate=fmt2.format(dateFmt);
    		}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
    	return newDate;
    }
    
    /**
     *  公共的显示时间
     * 当天 显示  HH:mm:ss
     * 超过显示  月-日
     * @param date
     * @return
     * @author jyt 2017年4月21日 下午5:32:22
     */
    public static String formateDateStringOneDayBySysMessage(Date date) {
        if (null == date) {
            return "";
        }
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        if (now.getTime() - date.getTime() >= 1) {
            if (date.getTime() < calendar.getTimeInMillis()) { // 昨天
                return dateToString(date, "MM-dd");
            } else {//当天
                return dateToString(date, "HH:mm:ss");
            }
        } 
        return dateToString(date, "MM-dd");
    }
 
    /**
     * 获取昨天日期
     * @return
     * @author zyg 2017年4月28日 下午5:32:22
     */
    public static String getYesterday(){
    	 Calendar   cal   =   Calendar.getInstance();
   	  	cal.add(Calendar.DATE,   -1);
   	  	return dateToString(cal.getTime(), null);
    }
  
    /**
     * 获取前天日期
     * @return
     * @author zyg 2017年4月28日 下午5:32:22
     */
    public static String getYesterdayS(){
    	 Calendar   cal   =   Calendar.getInstance();
   	  	cal.add(Calendar.DATE,   -2);
   	  	return dateToString(cal.getTime(), null);
    }
   
    /**
     * 
     * @Title: getStartTimeAndEndTimeOfMonth 
     * @Description: 获取某时间所在月的起止时间 
     * @param @param date
     * @param @return  
     * @return String[]    返回类型 
     * @throws 
     * @author LuGaogao
     * @date 2017年5月31日 下午4:33:53
     */
    public static String[] getStartTimeAndEndTimeOfMonth(Date date) {
    	if(null == date){
    		return null;
    	}
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int minDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, minDay);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date startTime = cal.getTime();
        //System.out.println("start:"+sdf.format(startTime));
        
        cal.set(Calendar.DAY_OF_MONTH, maxDay);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        Date endTime = cal.getTime();
        //System.out.println("end:"+sdf.format(endTime));
        
        return new String[]{sdf.format(startTime),sdf.format(endTime)};
    }
    /**
     * 
     * @Title: getStartDayAndEndDayOfMonth 
     * @Description: 获取某时间所在月的起止日期  
     * @param @param date
     * @param @return  
     * @return String[]    返回类型 
     * @throws 
     * @author LuGaogao
     * @date 2017年6月7日 下午7:02:37
     */
    public static String[] getStartDayAndEndDayOfMonth(Date date) {
    	if(null == date){
    		return null;
    	}
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	int minDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
    	int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    	cal.set(Calendar.DAY_OF_MONTH, minDay);
    	//cal.set(Calendar.HOUR_OF_DAY, 0);
    	//cal.set(Calendar.MINUTE, 0);
    	//cal.set(Calendar.SECOND, 0);
    	Date startTime = cal.getTime();
    	//System.out.println("start:"+sdf.format(startTime));
    	
    	cal.set(Calendar.DAY_OF_MONTH, maxDay);
    	//cal.set(Calendar.HOUR_OF_DAY, 23);
    	//cal.set(Calendar.MINUTE, 59);
    	//cal.set(Calendar.SECOND, 59);
    	Date endTime = cal.getTime();
    	//System.out.println("end:"+sdf.format(endTime));
    	
    	return new String[]{sdf.format(startTime),sdf.format(endTime)};
    } 
    /**
     * 获取时间差 年份
     * @param date
     * @return
     */
    public static Integer getMinusYear(Date date){
    	Calendar calOld = Calendar.getInstance();
    	calOld.setTime(date);
    	Calendar cal = Calendar.getInstance(); 
    	return cal.get(Calendar.YEAR) -calOld.get(Calendar.YEAR);
    }
    
    
}
