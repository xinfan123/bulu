package com.xinfan.blueblue.util;

import android.util.Log;

public class LogUtil {
	private static final String DEFAULT_TAG = "log";
	private static boolean logFlag;

	public static void d(Object paramObject, int paramInt) {
		d(paramObject, String.valueOf(paramInt));
	}

	public static void d(Object paramObject, String paramString) {
		String str = "log";
		if (paramObject != null)
			str = paramObject.getClass().getSimpleName();
		d(str, paramString);
	}

	public static void d(String paramString, int paramInt) {
		d(paramString, String.valueOf(paramInt));
	}

	public static void d(String paramString1, String paramString2) {
		if (paramString2 == null) {
			return;
		}

		if (logFlag)
			Log.d(paramString1, paramString2);
	}

	public static void e(Object paramObject, String paramString) {
		String str = "log";
		if (paramObject != null)
			str = paramObject.getClass().getSimpleName();
		e(str, paramString);
	}

	public static void e(Object paramObject, String paramString,
			Throwable paramThrowable) {
		if ((paramString == null) || (paramThrowable == null)) {
			return;
		}

		String str = "log";
		if (paramObject != null)
			str = paramObject.getClass().getSimpleName();

		if (logFlag)
			Log.e(str, paramString, paramThrowable);
	}

	public static void e(Object paramObject, Throwable paramThrowable) {
		if (paramThrowable == null) {
			return;
		}
		e(paramObject, paramThrowable.getMessage(), paramThrowable);
	}

	public static void e(String paramString1, String paramString2) {
		if (paramString2 == null) {
			return;
		}

		if (logFlag)
			Log.e(paramString1, paramString2);
	}

	public static void e(String paramString1, String paramString2,
			Throwable paramThrowable) {
		if ((paramString2 == null) || (paramThrowable == null)) {
			return;
		}
		if (logFlag)
			Log.e(paramString1, paramString2, paramThrowable);
	}

	public static void e(String paramString, Throwable paramThrowable) {
		if (paramThrowable == null) {
			return;
		}
		if (logFlag)
			Log.e(paramString, paramThrowable.getMessage(), paramThrowable);
	}

	public static void i(Object paramObject, String paramString) {
		String str = "log";
		if (paramObject != null)
			str = paramObject.getClass().getSimpleName();
		i(str, paramString);
	}

	public static void i(String paramString1, String paramString2) {
		if (paramString2 == null) {
			return;
		}
		if (logFlag)
			Log.i(paramString1, paramString2);
	}

	public static void v(Object paramObject, String paramString) {
		String str = "log";
		if (paramObject != null)
			str = paramObject.getClass().getSimpleName();
		v(str, paramString);
	}

	public static void v(String paramString1, String paramString2) {
		if (paramString2 == null) {
			return;
		}

		if (logFlag)
			Log.v(paramString1, paramString2);
	}

	public static void w(Object paramObject, String paramString) {
		String str = "log";
		if (paramObject != null)
			str = paramObject.getClass().getSimpleName();
		w(str, paramString);
	}

	public static void w(String paramString1, String paramString2) {
		if (paramString2 == null) {
			return;
		}
		if (logFlag)
			Log.w(paramString1, paramString2);
	}
}