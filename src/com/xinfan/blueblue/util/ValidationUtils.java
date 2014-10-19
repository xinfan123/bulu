package com.xinfan.blueblue.util;

import java.util.regex.Pattern;

public class ValidationUtils {

	private static Pattern mobilePattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

	public static boolean isMobile(String mobile) {
		return (mobilePattern.matcher(mobile)).matches();
	}

	public static boolean isNull(String str) {

		if (str == null || str.trim().length() == 0) {
			return true;
		}

		return false;
	}

	public static boolean isNotNull(String str) {

		return !isNull(str);
	}
}
