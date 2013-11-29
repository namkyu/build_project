package com.release.util;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.Vector;

import org.apache.commons.lang.time.FastDateFormat;

/**
 * The Class DateTimeUtil.
 */
public class DateTimeUtil {

	/**
	 * <pre>
	 * getFixedSimpleDateFormat
	 * 넘어온 fixedDate 를 pattern 형태로 변환 시킨다.
	 * fixedDate : 20100123230103
	 * pattern : yyyyMMdd
	 * 결과 : 20100123
	 * </pre>
	 *
	 * @param fixedDate
	 * @param pattern
	 * @return
	 */
	public static String getFixedSimpleDateFormat(String fixedDate, String pattern) {
		if (fixedDate == null || fixedDate.length() < 14) {
			return fixedDate;
		}
		int year = Integer.valueOf(fixedDate.substring(0, 4));
		int month = Integer.valueOf(fixedDate.substring(4, 6)) - 1; // calendar?? + 1
		int day = Integer.valueOf(fixedDate.substring(6, 8));
		int hh = Integer.valueOf(fixedDate.substring(8, 10));
		int mm = Integer.valueOf(fixedDate.substring(10, 12));
		int ss = Integer.valueOf(fixedDate.substring(12, 14));

		Calendar c = Calendar.getInstance();
		c.clear();
		c.set(year, month, day, hh, mm, ss);

		FastDateFormat ff = FastDateFormat.getInstance(pattern);
		return ff.format(c);
	}

	/**
	 * <pre>
	 * getNowSimpleDateFormat
	 * pattern을 지정하여 날짜를 return 받는다.
	 * pattern 사용 예 : yyyy-MM-dd HH:mm:ss
	 * </pre>
	 *
	 * @param pattern
	 * @return
	 */
	public static String getNowSimpleDateFormat(String pattern) {
		Calendar c = Calendar.getInstance();
		FastDateFormat ff = FastDateFormat.getInstance(pattern);
		return ff.format(c);
	}

	/**
	 * <pre>
	 * getTime
	 * 초까지 반환 한다.
	 * 2010년 02월 04일 09시 56분 42초
	 * </pre>
	 *
	 * @return
	 */
	public static String getTime() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
		return sdf.format(d);
	}

	/**
	 * <pre>
	 * getTime
	 * format에 맞는 날짜를 return
	 * </pre>
	 * @param format
	 * @return
	 */
	public static String getTime(String format) {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(d);
	}

	/**
	 * <pre>
	 * getCurrentDate
	 * yyyyMMdd 포멧으로 날짜 return
	 * </pre>
	 * @return
	 */
	public static String getCurrentDate() {
		return getCurrentDate("yyyyMMdd");
	}

	/**
	 * <pre>
	 * getCurrentDate
	 * format에 맞는 날짜 return
	 * </pre>
	 * @param format
	 * @return
	 */
	public static String getCurrentDate(String format) {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(d);
	}

	/**
	 * <pre>
	 * getThisMonth
	 * 현재 월 return
	 * </pre>
	 * @return
	 */
	public static String getThisMonth() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		return sdf.format(d);
	}

	/**
	 * <pre>
	 * getThisYear
	 * 현재 년 return
	 * </pre>
	 * @return
	 */
	public static String getThisYear() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		return sdf.format(d);
	}

	/**
	 * <pre>
	 * getCurrentTime
	 * 현재 시, 분, 초 return
	 * </pre>
	 * @return
	 */
	public static String getCurrentTime() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		return sdf.format(d);
	}

	/**
	 * <pre>
	 * getCurrentHourTime
	 * 현재 시 return
	 * </pre>
	 * @return
	 */
	public static String getCurrentHourTime() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		return sdf.format(d);
	}

	/**
	 * Gets the day interval.
	 *
	 * @param format the format
	 * @param distance the distance
	 *
	 * @return the day interval
	 */
	public static String getDayInterval(String format, int distance) {
		Calendar cal = getCalendar();
		cal.roll(Calendar.DATE, distance);
		Date d = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(d);
	}

	/**
	 * <pre>
	 * yesterDay
	 *
	 * </pre>
	 * @param date
	 * @return
	 */
	public static String yesterDay() {
		String date = getCurrentDate();
		long chStart = 0;
		DateFormat df = new SimpleDateFormat("yyyyMMdd");

		try {
			chStart = df.parse(date).getTime();
			chStart -= 86400000; // 하루 전
			Date aa = new Date(chStart);
			date = df.format(aa);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * Gets the day interval.
	 *
	 * @param dateString the date string
	 * @param format the format
	 * @param distance the distance
	 *
	 * @return the day interval
	 */
	public static String getDayInterval(String dateString, String format, int distance) {
		Calendar cal = getCalendar(dateString);
		cal.roll(Calendar.DATE, distance);
		Date d = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(d);
	}

	/**
	 * Gets the yesterday.
	 *
	 * @return the yesterday
	 */
	public static String getYesterday() {
		Calendar cal = getCalendar();
		cal.roll(Calendar.DATE, -1);
		Date d = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(d);
	}

	/**
	 * Gets the last month.
	 *
	 * @return the last month
	 */
	public static String getLastMonth() {
		Calendar cal = getCalendar();
		cal.roll(Calendar.MONTH, -1);
		Date d = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
		return sdf.format(d);
	}

	/**
	 * <pre>
	 * getCalendarMonth
	 * 한 자리의 month 를 리턴 받는다
	 * </pre>
	 * @return
	 */
	public static int getCalendarMonth() {
		Calendar cal = getCalendar();
		int month = cal.get(Calendar.MONDAY) + 1;
		return month;
	}

	/**
	 * Gets the dates.
	 *
	 * @param startDay the start day
	 * @param endDay the end day
	 *
	 * @return the dates
	 */
	public static String[] getDates(String startDay, String endDay) {
		Vector<String> v = new Vector<String>();
		v.addElement(startDay);
		Calendar cal = getCalendar();
		cal.setTime(string2Date(startDay));

		String nextDay = date2String(cal.getTime());

		while (!nextDay.equals(endDay)) {
			cal.add(Calendar.DATE, 1);
			nextDay = date2String(cal.getTime());
			v.addElement(nextDay);
		}

		String[] go = new String[v.size()];
		v.copyInto(go);
		return go;
	}

	/**
	 * Gets the calendar.
	 *
	 * @return the calendar
	 */
	public static Calendar getCalendar() {
		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("GMT+09:00"), Locale.KOREA);
		calendar.setTime(new Date());

		return calendar;
	}

	/**
	 * Gets the calendar.
	 *
	 * @param dateString the date string
	 *
	 * @return the calendar
	 */
	public static Calendar getCalendar(String dateString) {
		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("GMT+09:00"), Locale.KOREA);
		calendar.setTime(string2Date(dateString, "yyyyMMdd"));

		return calendar;
	}

	/**
	 * Gets the calendar.
	 *
	 * @param date the date
	 *
	 * @return the calendar
	 */
	public static Calendar getCalendar(Date date) {
		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("GMT+09:00"), Locale.KOREA);
		calendar.setTime(date);

		return calendar;
	}

	/**
	 * Date2 string.
	 *
	 * @param d the d
	 *
	 * @return the string
	 */
	public static String date2String(java.util.Date d) {
		return date2String(d, "yyyyMMdd");
	}

	/**
	 * Date2 string.
	 *
	 * @param d the d
	 * @param format the format
	 *
	 * @return the string
	 */
	public static String date2String(java.util.Date d, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(d);
	}

	/**
	 * String2 date.
	 *
	 * @param s the s
	 *
	 * @return the java.util. date
	 */
	public static java.util.Date string2Date(String s) {
		return string2Date(s, "yyyy/MM/dd");
	}

	/**
	 * String2 date time.
	 *
	 * @param s the s
	 *
	 * @return the java.util. date
	 */
	public static java.util.Date string2DateTime(String s) {
		return string2Date(s, "yyyy/MM/dd HH:mm:ss");
	}

	/**
	 * String2 date.
	 *
	 * @param s the s
	 * @param format the format
	 *
	 * @return the java.util. date
	 */
	public static java.util.Date string2Date(String s, String format) {
		java.util.Date d = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			d = sdf.parse(s, new ParsePosition(0));
		} catch (Exception e) {
			throw new RuntimeException("Date format not valid.");
		}
		return d;
	}

	/**
	 * Gets the day distance.
	 *
	 * @param startDate the start date
	 * @param endDate the end date
	 *
	 * @return the day distance
	 *
	 * @throws Exception the exception
	 */
	public static long getDayDistance(String startDate, String endDate) throws Exception {
		return getDayDistance(startDate, endDate, null);
	}

	/**
	 * Gets the day distance.
	 *
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param format the format
	 *
	 * @return the day distance
	 *
	 * @throws Exception the exception
	 */
	public static long getDayDistance(String startDate, String endDate, String format) throws Exception {
		if (format == null)
			format = "yyyyMMdd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date sDate;
		Date eDate;
		long day2day = 0;
		try {
			sDate = sdf.parse(startDate);
			eDate = sdf.parse(endDate);
			day2day = (eDate.getTime() - sDate.getTime()) / (1000 * 60 * 60 * 24);
		} catch (Exception e) {
			throw new Exception("wrong format string");
		}

		return Math.abs(day2day);
	}

	/**
	 * Gets the uS time.
	 *
	 * @return the uS time
	 */
	public static String getUSTime() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.US);
		sdf.setTimeZone((new SimpleTimeZone(0, "GMT")));
		return sdf.format(d);
	}

	/**
	 * Parses the time zone str.
	 *
	 * @param str the str
	 *
	 * @return the time zone
	 */
	public static TimeZone ParseTimeZoneStr(String str) {
		if (str.length() > 9) {
			return null;
		}

		if (str.equals("LOCAL")) {
			return TimeZone.getDefault();
		}

		if (str.startsWith("UTC")) {
			str = str.replaceFirst("UTC", "GMT");
		} else if (str.startsWith("GMT") == false) {
			return null;
		}

		if (str.equals("GMT")) {
			return TimeZone.getTimeZone(str);
		}

		if (str.length() < 5) {
			return null;
		}

		if (str.charAt(3) != '+' && str.charAt(3) != '-') {
			return null;
		}

		String str2 = str.substring(4);
		String[] hm = str2.split(":");

		if (hm.length > 2) {
			return null;
		}

		try {
			Integer.parseInt(hm[0]);
		} catch (NumberFormatException e) {
			return null;
		}

		if (hm.length > 1) {
			try {
				Integer.parseInt(hm[1]);

				// 이상하게 GMT+9:1을 넣으면 minute 1 부분이 제대로 안된다.
				// GMT+9:01로 넣어야 제대로 작동한다. 따라서 한자리면 두자리로 만들어 준다.
				if (hm[1].length() < 2) {
					int i = str.indexOf(":");
					str = str.substring(0, i + 1) + "0" + str.substring(i + 1);
				}
			} catch (NumberFormatException e) {
				return null;
			}
		}

		return TimeZone.getTimeZone(str);
	}

	/**
	 * <pre>
	 * dateParseFormat
	 *
	 * </pre>
	 * @param strDate
	 * @param format
	 * @return
	 * @throws Exception
	 */
	public static String dateParseFormat(String strDate, String format) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = sdf.parse(strDate);

		SimpleDateFormat transSdf = new SimpleDateFormat(format);
		String formatDate = transSdf.format(date);
		return formatDate;
	}
}
