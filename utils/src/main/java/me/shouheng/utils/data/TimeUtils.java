package me.shouheng.utils.data;

import androidx.annotation.NonNull;
import android.text.format.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import me.shouheng.utils.UtilsApp;
import me.shouheng.utils.constant.TimeConstants;

/**
 * @author Shouheng Wang (shouheng2020@gmail.com)
 * @version 2019/5/12 16:07
 */
public final class TimeUtils {
    public static final long SECOND            = 1000;
    public static final long MINUTE            = 60 * SECOND;
    public static final long HOUR              = 60 * MINUTE;
    public static final long DAY               = 24 * HOUR;
    private static final int[]    ZODIAC_FLAGS = {20, 19, 21, 21, 21, 22, 23, 23, 23, 24, 23, 22};
    private static final String[] ZODIAC       = {
            "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座",
            "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座"
    };
    private static final String[] CHINESE_ZODIAC =
            {"猴", "鸡", "狗", "猪", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊"};

    // region *----------------------------- 昨天｜今天｜明天 ---------------------------------*

    public static long now() {
        return System.currentTimeMillis();
    }

    public static String nowString() {
        return toString(System.currentTimeMillis(), getDefaultFormat());
    }

    public static String nowString(@NonNull final DateFormat format) {
        return toString(System.currentTimeMillis(), format);
    }

    public static Date nowDate() {
        return new Date();
    }

    /** Get millisecond of beginning of today. */
    public static long beginOfToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,  0);
        calendar.set(Calendar.MINUTE,       0);
        calendar.set(Calendar.SECOND,       0);
        calendar.set(Calendar.MILLISECOND,  0);
        return calendar.getTimeInMillis();
    }

    /** Get millisecond of beginning of this week. */
    public static long beginOfWeek(boolean sundayFirst) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,  0);
        calendar.set(Calendar.MINUTE,       0);
        calendar.set(Calendar.SECOND,       0);
        calendar.set(Calendar.MILLISECOND,  0);
        long millis = calendar.getTimeInMillis();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (sundayFirst) {
            millis -= (dayOfWeek-1) * DAY;
        } else {
            millis -= (dayOfWeek == Calendar.SUNDAY ? 6 : dayOfWeek-2) * DAY;
        }
        return millis;
    }

    /** Get millisecond of beginning of this month. */
    public static long beginOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE,         1);
        calendar.set(Calendar.HOUR_OF_DAY,  0);
        calendar.set(Calendar.MINUTE,       0);
        calendar.set(Calendar.SECOND,       0);
        calendar.set(Calendar.MILLISECOND,  0);
        return calendar.getTimeInMillis();
    }

    /** Get millisecond of beginning of this year. */
    public static long beginOfYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH,        Calendar.JANUARY);
        calendar.set(Calendar.DATE,         1);
        calendar.set(Calendar.HOUR_OF_DAY,  0);
        calendar.set(Calendar.MINUTE,       0);
        calendar.set(Calendar.SECOND,       0);
        calendar.set(Calendar.MILLISECOND,  0);
        return calendar.getTimeInMillis();
    }

    /** Get millisecond of end of today. */
    public static long endOfToday() {
        return beginOfToday() + DAY - 1;
    }

    /** Get millisecond of end of this week. */
    public static long endOfWeek(boolean sundayFirst) {
        return beginOfWeek(sundayFirst) + 7*DAY - 1;
    }

    /** Get millisecond of end of this month. */
    public static long endOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH,        calendar.get(Calendar.MONTH)+1);
        calendar.set(Calendar.DATE,         1);
        calendar.set(Calendar.HOUR_OF_DAY,  0);
        calendar.set(Calendar.MINUTE,       0);
        calendar.set(Calendar.SECOND,       0);
        calendar.set(Calendar.MILLISECOND,  0);
        return calendar.getTimeInMillis()-1;
    }

    /** Get millisecond of end of this year. */
    public static long endOfYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,         calendar.get(Calendar.YEAR)+1);
        calendar.set(Calendar.MONTH,        Calendar.JANUARY);
        calendar.set(Calendar.DATE,         1);
        calendar.set(Calendar.HOUR_OF_DAY,  0);
        calendar.set(Calendar.MINUTE,       0);
        calendar.set(Calendar.SECOND,       0);
        calendar.set(Calendar.MILLISECOND,  0);
        return calendar.getTimeInMillis()-1;
    }

    // endregion

    // region *---------------------------------- 转换 --------------------------------------*

    public static String toString(final long millis) {
        return toString(millis, getDefaultFormat());
    }

    public static String toString(final long millis, @NonNull final DateFormat format) {
        return format.format(new Date(millis));
    }

    /**
     * Time format output by system flags. See sample app for details.
     *
     * @param millis millis of time
     * @param flags  flags
     * @return       date string output
     */
    public static String toString(long millis, int flags) {
        return DateUtils.formatDateTime(UtilsApp.getApp(), millis, flags);
    }

    public static long toMillis(final String time) {
        return toMillis(time, getDefaultFormat());
    }

    public static long toMillis(final String time, @NonNull final DateFormat format) {
        try {
            return format.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static Date toDate(final String time) {
        return toDate(time, getDefaultFormat());
    }

    public static Date toDate(final String time, @NonNull final DateFormat format) {
        try {
            return format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String toString(final Date date) {
        return toString(date, getDefaultFormat());
    }

    public static String toString(final Date date, @NonNull final DateFormat format) {
        return format.format(date);
    }

    public static String toString(final Date date, int flags) {
        return toString(date.getTime(), flags);
    }

    public static long toMillis(final Date date) {
        return date.getTime();
    }

    public static Date toDate(final long millis) {
        return new Date(millis);
    }

    // endregion

    // region *---------------------------------- 跨度 --------------------------------------*

    public static long span(final String time1,
                            final String time2,
                            @TimeConstants.Unit final int unit) {
        return span(time1, time2, getDefaultFormat(), unit);
    }

    public static long span(final String time1,
                            final String time2,
                            @NonNull final DateFormat format,
                            @TimeConstants.Unit final int unit) {
        return toTimeSpan(toMillis(time1, format) - toMillis(time2, format), unit);
    }

    public static long span(final Date date1,
                            final Date date2,
                            @TimeConstants.Unit final int unit) {
        return toTimeSpan(toMillis(date1) - toMillis(date2), unit);
    }

    public static long span(final long millis1,
                            final long millis2,
                            @TimeConstants.Unit final int unit) {
        return toTimeSpan(millis1 - millis2, unit);
    }

    // endregion

    // region *---------------------------------- 判断 --------------------------------------*

    /**
     * Return whether it is today.
     * <p>The pattern is {@code yyyy-MM-dd HH:mm:ss}.</p>
     *
     * @param time The formatted time string.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isToday(final String time) {
        return isToday(toMillis(time, getDefaultFormat()));
    }

    /**
     * Return whether it is today.
     *
     * @param time   The formatted time string.
     * @param format The format.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isToday(final String time, @NonNull final DateFormat format) {
        return isToday(toMillis(time, format));
    }

    /**
     * Return whether it is today.
     *
     * @param date The date.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isToday(final Date date) {
        return isToday(date.getTime());
    }

    /**
     * Return whether it is today.
     *
     * @param millis The milliseconds.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isToday(final long millis) {
        long wee = getWeeOfToday();
        return millis >= wee && millis < wee + TimeConstants.DAY;
    }

    public static boolean isLeapYear(final String time) {
        return isLeapYear(toDate(time, getDefaultFormat()));
    }

    public static boolean isLeapYear(final String time, @NonNull final DateFormat format) {
        return isLeapYear(toDate(time, format));
    }

    public static boolean isLeapYear(final Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        return isLeapYear(year);
    }

    public static boolean isLeapYear(final long millis) {
        return isLeapYear(toDate(millis));
    }

    public static boolean isLeapYear(final int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    /**
     * Return whether it is am.
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isAm() {
        Calendar cal = Calendar.getInstance();
        return cal.get(GregorianCalendar.AM_PM) == 0;
    }

    /**
     * Return whether it is am.
     * <p>The pattern is {@code yyyy-MM-dd HH:mm:ss}.</p>
     *
     * @param time The formatted time string.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isAm(final String time) {
        return getValueByCalendarField(time, getDefaultFormat(), GregorianCalendar.AM_PM) == 0;
    }

    /**
     * Return whether it is am.
     *
     * @param time   The formatted time string.
     * @param format The format.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isAm(final String time,
                               @NonNull final DateFormat format) {
        return getValueByCalendarField(time, format, GregorianCalendar.AM_PM) == 0;
    }

    /**
     * Return whether it is am.
     *
     * @param date The date.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isAm(final Date date) {
        return getValueByCalendarField(date, GregorianCalendar.AM_PM) == 0;
    }

    /**
     * Return whether it is am.
     *
     * @param millis The milliseconds.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isAm(final long millis) {
        return getValueByCalendarField(millis, GregorianCalendar.AM_PM) == 0;
    }

    /**
     * Return whether it is am.
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isPm() {
        return !isAm();
    }

    /**
     * Return whether it is am.
     * <p>The pattern is {@code yyyy-MM-dd HH:mm:ss}.</p>
     *
     * @param time The formatted time string.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isPm(final String time) {
        return !isAm(time);
    }

    /**
     * Return whether it is am.
     *
     * @param time   The formatted time string.
     * @param format The format.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isPm(final String time,
                               @NonNull final DateFormat format) {
        return !isAm(time, format);
    }

    /**
     * Return whether it is am.
     *
     * @param date The date.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isPm(final Date date) {
        return !isAm(date);
    }

    /**
     * Return whether it is am.
     *
     * @param millis The milliseconds.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isPm(final long millis) {
        return !isAm(millis);
    }

    // endregion

    // region *------------------------------- 属相｜星座 ------------------------------------*

    /**
     * Return the Chinese zodiac.
     * <p>The pattern is {@code yyyy-MM-dd HH:mm:ss}.</p>
     *
     * @param time The formatted time string.
     * @return the Chinese zodiac
     */
    public static String getChineseZodiac(final String time) {
        return getChineseZodiac(toDate(time, getDefaultFormat()));
    }

    /**
     * Return the Chinese zodiac.
     *
     * @param time   The formatted time string.
     * @param format The format.
     * @return the Chinese zodiac
     */
    public static String getChineseZodiac(final String time, @NonNull final DateFormat format) {
        return getChineseZodiac(toDate(time, format));
    }

    /**
     * Return the Chinese zodiac.
     *
     * @param date The date.
     * @return the Chinese zodiac
     */
    public static String getChineseZodiac(final Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return CHINESE_ZODIAC[cal.get(Calendar.YEAR) % 12];
    }

    /**
     * Return the Chinese zodiac.
     *
     * @param millis The milliseconds.
     * @return the Chinese zodiac
     */
    public static String getChineseZodiac(final long millis) {
        return getChineseZodiac(toDate(millis));
    }

    /**
     * Return the Chinese zodiac.
     *
     * @param year The year.
     * @return the Chinese zodiac
     */
    public static String getChineseZodiac(final int year) {
        return CHINESE_ZODIAC[year % 12];
    }

    /**
     * Return the zodiac.
     * <p>The pattern is {@code yyyy-MM-dd HH:mm:ss}.</p>
     *
     * @param time The formatted time string.
     * @return the zodiac
     */
    public static String getZodiac(final String time) {
        return getZodiac(toDate(time, getDefaultFormat()));
    }

    /**
     * Return the zodiac.
     *
     * @param time   The formatted time string.
     * @param format The format.
     * @return the zodiac
     */
    public static String getZodiac(final String time, @NonNull final DateFormat format) {
        return getZodiac(toDate(time, format));
    }

    /**
     * Return the zodiac.
     *
     * @param date The date.
     * @return the zodiac
     */
    public static String getZodiac(final Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return getZodiac(month, day);
    }

    /**
     * Return the zodiac.
     *
     * @param millis The milliseconds.
     * @return the zodiac
     */
    public static String getZodiac(final long millis) {
        return getZodiac(toDate(millis));
    }

    /**
     * Return the zodiac.
     *
     * @param month The month.
     * @param day   The day.
     * @return the zodiac
     */
    public static String getZodiac(final int month, final int day) {
        return ZODIAC[day >= ZODIAC_FLAGS[month - 1]
                ? month - 1
                : (month + 10) % 12];
    }

    // endregion

    // region *------------------------------- Calendar ------------------------------------*

    /**
     * Returns the value of the given calendar field.
     *
     * @param field The given calendar field.
     *              <ul>
     *              <li>{@link Calendar#ERA}</li>
     *              <li>{@link Calendar#YEAR}</li>
     *              <li>{@link Calendar#MONTH}</li>
     *              <li>...</li>
     *              <li>{@link Calendar#DST_OFFSET}</li>
     *              </ul>
     * @return the value of the given calendar field
     */
    public static int getValueByCalendarField(final int field) {
        Calendar cal = Calendar.getInstance();
        return cal.get(field);
    }

    /**
     * Returns the value of the given calendar field.
     * <p>The pattern is {@code yyyy-MM-dd HH:mm:ss}.</p>
     *
     * @param time  The formatted time string.
     * @param field The given calendar field.
     *              <ul>
     *              <li>{@link Calendar#ERA}</li>
     *              <li>{@link Calendar#YEAR}</li>
     *              <li>{@link Calendar#MONTH}</li>
     *              <li>...</li>
     *              <li>{@link Calendar#DST_OFFSET}</li>
     *              </ul>
     * @return the value of the given calendar field
     */
    public static int getValueByCalendarField(final String time, final int field) {
        return getValueByCalendarField(toDate(time, getDefaultFormat()), field);
    }

    /**
     * Returns the value of the given calendar field.
     *
     * @param time   The formatted time string.
     * @param format The format.
     * @param field  The given calendar field.
     *               <ul>
     *               <li>{@link Calendar#ERA}</li>
     *               <li>{@link Calendar#YEAR}</li>
     *               <li>{@link Calendar#MONTH}</li>
     *               <li>...</li>
     *               <li>{@link Calendar#DST_OFFSET}</li>
     *               </ul>
     * @return the value of the given calendar field
     */
    public static int getValueByCalendarField(final String time,
                                              @NonNull final DateFormat format,
                                              final int field) {
        return getValueByCalendarField(toDate(time, format), field);
    }

    /**
     * Returns the value of the given calendar field.
     *
     * @param date  The date.
     * @param field The given calendar field.
     *              <ul>
     *              <li>{@link Calendar#ERA}</li>
     *              <li>{@link Calendar#YEAR}</li>
     *              <li>{@link Calendar#MONTH}</li>
     *              <li>...</li>
     *              <li>{@link Calendar#DST_OFFSET}</li>
     *              </ul>
     * @return the value of the given calendar field
     */
    public static int getValueByCalendarField(final Date date, final int field) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(field);
    }

    /**
     * Returns the value of the given calendar field.
     *
     * @param millis The milliseconds.
     * @param field  The given calendar field.
     *               <ul>
     *               <li>{@link Calendar#ERA}</li>
     *               <li>{@link Calendar#YEAR}</li>
     *               <li>{@link Calendar#MONTH}</li>
     *               <li>...</li>
     *               <li>{@link Calendar#DST_OFFSET}</li>
     *               </ul>
     * @return the value of the given calendar field
     */
    public static int getValueByCalendarField(final long millis, final int field) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);
        return cal.get(field);
    }

    // endregion

    // region *---------------------------------- 内部 --------------------------------------*

    private static final ThreadLocal<SimpleDateFormat> SDF_THREAD_LOCAL = new ThreadLocal<>();

    private static SimpleDateFormat getDefaultFormat() {
        SimpleDateFormat simpleDateFormat = SDF_THREAD_LOCAL.get();
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            SDF_THREAD_LOCAL.set(simpleDateFormat);
        }
        return simpleDateFormat;
    }

    private static long getWeeOfToday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * TODO 下面只能算是一种计算方式，即指定时间跨度中天的个数，但是：
     * 1. 不到一天怎么算？
     * 2. 起止日期怎么算？
     */
    private static long toTimeSpan(final long millis, @TimeConstants.Unit final int unit) {
        return millis / unit;
    }

    private TimeUtils() {
        throw new UnsupportedOperationException("u can't initialize me!");
    }

    // endregion
}
