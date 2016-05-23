package com.release.util;

import java.util.Calendar;

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
	 * <pre>
	 * getNowSimpleDateFormat
	 *
	 * <pre>
	 * @param pattern
	 * @return
	 */
	public static String getNowSimpleDateFormat(String pattern) {
        Calendar c = Calendar.getInstance();
        FastDateFormat ff = FastDateFormat.getInstance(pattern);
        return ff.format(c);
    }
}
