package com.release.util;

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
 * @FileName : DateTimeUtil.java
 * @Project : TEST_PROJECT
 * @Date : 2012. 1. 20.
 * @작성자 : 이남규
 * @프로그램설명 :
 */
public class DateTimeUtil {

    /**
     * YYYYMMDDhhmmss' in the form of 14-digit date string SimpleDateFomat converted to a format that supports them.
     *
     * @param fixedDate 'YYYYMMDDhhmmss' , <b> Must be 14 length. </br>
     * @param pattern   format pattern
     *
     * @return formated string
     */
    public static String getFixedSimpleDateFormat(String fixedDate, String pattern) {
        if (fixedDate == null || fixedDate.length() < 14) {
            return fixedDate;
        }
        int year = Integer.valueOf(fixedDate.substring(0, 4));
        int month = Integer.valueOf(fixedDate.substring(4, 6)) - 1;  // calendar�� +1
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
     * Gets the now simple date format.
     *
     * @param pattern the pattern
     *
     * @return the now simple date format
     */
    public static String getNowSimpleDateFormat(String pattern) {
        Calendar c = Calendar.getInstance();
        FastDateFormat ff = FastDateFormat.getInstance(pattern);
        return ff.format(c);
    }

    /**
     * 현재 날짜를 yyyy년 MM월 dd일 HH시 mm분의 형태로 값을 얻어낸다.
     *
     * @return yyyy년 MM월 dd일 HH시 mm분의 형태로 바뀐 현재 시간값
     */
    public static String getTime()
    {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
        return sdf.format(d);
    }

    /**
     * 현재 날짜를 입력받은 포맷의 형태로 변환하여 결과값을 리턴하도로 한다. 예) format : yyyyMMddHHmmss -->
     * 20031130124130 로 결과값 반환
     *
     * @param format the format
     *
     * @return the time
     */
    public static String getTime(String format)
    {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(d);
    }

    /**
     * 현재 날짜를 yyyy/mm/dd까지의 형태로 추출해낸다.
     *
     * @return yyyy/mm/dd형태로 변경되된 문자열값
     */
    public static String getCurrentDate()
    {
        return getCurrentDate("yyyyMMdd");
    }

    /**
     * 현재 날짜를 yyyy/mm/dd까지의 형태로 추출해낸다.
     *
     * @param format the format
     *
     * @return yyyy/mm/dd형태로 변경되된 문자열값
     */
    public static String getCurrentDate(String format)
    {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(d);
    }

    /**
     * 현재 월을 년도와 함께 추출해낸다.
     *
     * @return yyyy/mm형태로 변경되된 문자열값
     */
    public static String getThisMonth()
    {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        return sdf.format(d);
    }

    /**
     * 현재 년도를 추출해낸다.
     *
     * @return yyyy형태로 변경되된 문자열값
     */
    public static String getThisYear()
    {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return sdf.format(d);
    }

    /**
     * 현재 시간을 03:34 형태의 시/분으로 표시한다.
     *
     * @return hh:mm형태로 변경되된 문자열값
     */
    public static String getCurrentTime()
    {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        return sdf.format(d);
    }

    /**
     * 오늘을 기준으로 입력받은 날자의 날짜를 알아낸다.
     *
     * @param format the format
     * @param distance the distance
     *
     * @return yyyy/mm/dd형태로 변경되된 문자열값
     */
    public static String getDayInterval(String format, int distance)
    {
        Calendar cal = getCalendar();
        cal.roll(Calendar.DATE, distance);
        Date d = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(d);
    }

    /**
     * 입력받은 날자의 날짜를 기준으로 해당일의 과거나 미래일을 알아낸다.
     *
     * @param dateString the date string
     * @param format the format
     * @param distance the distance
     * @return format형태로 변경되된 문자열값
     */
    public static String getDayInterval(String dateString, String format, int distance)
    {
        Calendar cal = getCalendar(dateString);
        cal.roll(Calendar.DATE, distance);
        Date d = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(d);
    }

    /**
     * 오늘을 기준으로 어제의 날짜를 알아낸다.
     *
     * @return yyyy/mm/dd형태로 변경되된 문자열값
     */
    public static String getYesterday()
    {
        Calendar cal = getCalendar();
        cal.roll(Calendar.DATE, -1);
        Date d = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(d);
    }

    /**
     * 현재 얻어낸 날짜의 마지막 달을 알아낸다.
     *
     * @return yyyy/mm형태로 변경되된 문자열값
     */
    public static String getLastMonth()
    {
        Calendar cal = getCalendar();
        cal.roll(Calendar.MONTH, -1);
        Date d = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
        return sdf.format(d);
    }

    /**
     * 주어진 시작일과 종료일에 사이의 값들을 문자형태의 배열로 만들어낸다.
     *
     * @param startDay 생성하고자 하는 값을 시작일
     * @param endDay 생성하고자 하는 값의 종료일
     *
     * @return 시작일과 종료일사이의 날짜값들을 가진 문자열배열
     */
    public static String[] getDates(String startDay, String endDay)
    {
        Vector<String> v = new Vector<String>();
        v.addElement(startDay);
        Calendar cal = getCalendar();
        cal.setTime(string2Date(startDay));

        String nextDay = date2String(cal.getTime());

        while (!nextDay.equals(endDay))
        {
            cal.add(Calendar.DATE, 1);
            nextDay = date2String(cal.getTime());
            v.addElement(nextDay);
        }

        String[] go = new String[v.size()];
        v.copyInto(go);
        return go;
    }

    /**
     * GMT기준시간중의 한국표준시를 반환한다.
     *
     * return GMT+09:00형태의 대한민국표준시
     *
     * @return the calendar
     */
    public static Calendar getCalendar()
    {
        Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("GMT+09:00"), Locale.KOREA);
        calendar.setTime(new Date());

        return calendar;
    }

    /**
     * GMT기준시간중의 한국표준시를 반환한다.
     *
     * return GMT+09:00형태의 대한민국표준시
     *
     * @param dateString the date string
     *
     * @return the calendar
     */
    public static Calendar getCalendar(String dateString)
    {
        Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("GMT+09:00"), Locale.KOREA);
        calendar.setTime(string2Date(dateString, "yyyyMMdd"));

        return calendar;
    }

    /**
     * GMT기준시간중의 한국표준시를 반환한다.
     *
     * return GMT+09:00형태의 대한민국표준시
     *
     * @param date the date
     *
     * @return the calendar
     */
    public static Calendar getCalendar(Date date)
    {
        Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("GMT+09:00"), Locale.KOREA);
        calendar.setTime(date);

        return calendar;
    }

    /**
     * 날짜형태의 데이터를 yyyy/mm/dd형태로 바꿔주는 메소드 Date -> String (2000/09/25).
     *
     * @param d 설정된 날짜표시형태로 변경할 date객체
     *
     * @return yyyy/mm/dd형태로 변경되어진 문자열
     */
    public static String date2String(java.util.Date d)
    {
        return date2String(d, "yyyyMMdd");
    }

    /**
     * 날짜형태의 데이터를 사용자정의형태로 바꿔주는 메소드 Date -> String (2000/09/25).
     *
     * @param d 설정된 날짜표시형태로 변경할 date객체
     * @param format the format
     *
     * @return yyyy/mm/dd형태로 변경되어진 문자열
     */
    public static String date2String(java.util.Date d, String format)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(d);
    }

    /**
     * 문자열 데이터를 yyyy/mm/dd형태의 Date형태의 객체로 바꾸어준다 String -> Date (2000/09/25).
     *
     * @param s Date형태로 만들게 될 yyyy/mm/dd형태의 문자열
     *
     * @return yyyy/mm/dd형태로 변경되어진 Date객체
     */
    public static java.util.Date string2Date(String s)
    {
        return string2Date(s, "yyyy/MM/dd");
    }

    /**
     * 문자열 데이터를 yyyy/mm/dd HH:mm:ss형태의 Date형태의 객체로 바꾸어준다 String -> Date (2000/09/25 12:12:12).
     *
     * @param s Date형태로 만들게 될 yyyy/mm/dd형태의 문자열
     *
     * @return yyyy/mm/dd형태로 변경되어진 Date객체
     */
    public static java.util.Date string2DateTime(String s)
    {
        return string2Date(s, "yyyy/MM/dd HH:mm:ss");
    }


    /**
     * 문자열 데이터를 사용자형태의 Date형태의 객체로 바꾸어준다 String -> Date (2000/09/25).
     *
     * @param s Date형태로 만들게 될 yyyy/mm/dd형태의 문자열
     * @param format the format
     *
     * @return yyyy/mm/dd형태로 변경되어진 Date객체
     */
    public static java.util.Date string2Date(String s, String format)
    {
        java.util.Date d = null;
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            d = sdf.parse(s, new ParsePosition(0));
        }
        catch (Exception e)
        {
            throw new RuntimeException("Date format not valid.");
        }
        return d;
    }

    /**
     * 두 날짜 사이의 차이.
     *
     * @param startDate 시작 날짜
     * @param endDate 종료 날짜
     *
     * @return long 날짜 차이
     *
     * @throws Exception the exception
     */
    public static long getDayDistance(String startDate, String endDate) throws Exception
    {
        return getDayDistance(startDate, endDate, null);
    }

    /**
     * 두 날짜 사이의 차이.
     *
     * @param startDate 시작 날짜
     * @param endDate 종료 날짜
     * @param format 날짜 형식
     *
     * @return long 날짜 차이
     *
     * @throws Exception the exception
     */
    public static long getDayDistance(String startDate, String endDate, String format) throws Exception
    {
        if (format == null) {
			format = "yyyyMMdd";
		}
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date sDate;
        Date eDate;
        long day2day = 0;
        try
        {
            sDate = sdf.parse(startDate);
            eDate = sdf.parse(endDate);
            day2day = (eDate.getTime() - sDate.getTime()) / (1000 * 60 * 60 * 24);
        }
        catch (Exception e)
        {
            throw new Exception("wrong format string");
        }

        return Math.abs(day2day);
    }

    /**
     * Gets the uS time.
     *
     * @return the uS time
     */
    public static String getUSTime()
    {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.US);
        sdf.setTimeZone((new SimpleTimeZone(0, "GMT")));
        return sdf.format(d);
    }

    /**
     * TPVR 테이블의 TimeZone에 들어있는 String을 가지고 TimeZone을 만들어 낸다.
     * 이 함수가 리턴하는 TimeZone을 가지고 Calendar.getInstance( timeZone )을 호출하면 된다.
     *
     * @param str TPVR 테이블의 TimeZone에 들어있는 String( ex) UTC, UTC+9, UTC+9:30, LOCAL ... )
     *
     * @return str을 가지고 만든 TimeZone, str이 잘못 되었으면 null을 리턴한다.
     *
     * @author zoomin
     */
    public static TimeZone ParseTimeZoneStr( String str )
    {
        if( str.length() > 9 )
        {
            // System.out.println( "Malformed:Too Long String");
            return null;
        }

        if( str.equals( "LOCAL" ) )
        {
            return TimeZone.getDefault();
        }

        if( str.startsWith( "UTC" ) )
        {
            str = str.replaceFirst( "UTC", "GMT" );
        }
        else if( str.startsWith( "GMT" ) == false )
        {
            // System.out.println( "Malformed:Must Start 'GMT' or 'UTC'");
            return null;
        }

        if( str.equals( "GMT" ) )
        {
            return TimeZone.getTimeZone( str );
        }

        if( str.length() < 5 )
        {
            // System.out.println( "Malformed:Too Short String");
            return null;
        }

        if( str.charAt( 3 ) != '+' && str.charAt( 3 ) != '-' )
        {
            // System.out.println( "Malformed:Sign Part Error");
            return null;
        }

        String str2 = str.substring( 4 );
        String[] hm = str2.split( ":" );

        if( hm.length > 2 )
        {
            // System.out.println( "Malformed:Too Many Hour or Minute Part");
            return null;
        }

        try
        {
            Integer.parseInt( hm[0] );
        }
        catch( NumberFormatException e )
        {
            // System.out.println( "Malformed:Hour Part Error");
            return null;
        }

        if( hm.length > 1 )
        {
            try
            {
                Integer.parseInt( hm[1] );

                // 이상하게 GMT+9:1을 넣으면 minute 1 부분이 제대로 안된다.
                // GMT+9:01로 넣어야 제대로 작동한다. 따라서 한자리면 두자리로 만들어 준다.
                if( hm[1].length() < 2 )
                {
                    int i = str.indexOf( ":" );
                    str = str.substring( 0, i + 1 ) + "0" + str.substring( i + 1 );
                }
            }
            catch( NumberFormatException e )
            {
                // System.out.println( "Malformed:Minute Part Error");
                return null;
            }
        }

        return TimeZone.getTimeZone( str );
    }
}
