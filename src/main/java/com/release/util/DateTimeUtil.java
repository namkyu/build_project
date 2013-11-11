/*
 * Copyright (c) 2013 namkyu.
 * All right reserved.
 *
 */
package com.release.util;

import java.util.Calendar;

import org.apache.commons.lang.time.FastDateFormat;


/**
 * The Class DateTimeUtil.
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
