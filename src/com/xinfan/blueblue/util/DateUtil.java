package com.xinfan.blueblue.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
	private static final String DEFAULT_PATTERN = "yyyy-MM-dd";
	private static final int TEN = 10;
	private static final int TIME_UNIT = 60;

	private static String addZero(int paramInt) {
		if (paramInt < 10)
			;
		for (String str = "0" + paramInt;; str = String.valueOf(paramInt))
			return str;
	}

	public static Date getDate(String paramString) {
		return getDate(paramString, null);
	}

	public static Date getDate(String paramString1, String paramString2) {
		if (paramString1 == null) {
			return null;
		}

		if (paramString2 == null) {
			paramString2 = "yyyy-MM-dd";
		}

		try {
			Date localDate = new SimpleDateFormat(paramString2,
					Locale.getDefault()).parse(paramString1);
			return localDate;
		} catch (ParseException localParseException) {
			localParseException.printStackTrace();
			return null;
		}
	}

	public static String getDateString(Date paramDate) {
		return getDateString(paramDate, null);
	}

	public static String getDateString(Date paramDate, String paramString) {
		if (paramString == null)
			paramString = "yyyy-MM-dd";

		try {
			SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
					paramString, Locale.getDefault());
			return localSimpleDateFormat.format(paramDate);
		} catch (Exception localException) {
			throw new RuntimeException("不支持的日期模式", localException);
		}
	}

	public static String getFormateTime(int paramInt) {
		int i = paramInt / 60;
		int j = paramInt % 60;
		return addZero(i) + ":" + addZero(j);
	}

	public static Date getSystemDate() {
		return getSystemDate(null);
	}

	public static Date getSystemDate(String paramString) {
		Calendar localCalendar = Calendar.getInstance();
		localCalendar.setTimeInMillis(System.currentTimeMillis());
		Date localDate = localCalendar.getTime();
		if (paramString != null)
			localDate = getDate(getDateString(localDate, paramString));
		return localDate;
	}

	public static String getSystemDateString() {
		return getDateString(getSystemDate(), null);
	}

	public static String getSystemDateString(String paramString) {
		return getDateString(getSystemDate(), paramString);
	}
}