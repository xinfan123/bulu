package com.xinfan.blueblue.util;

import java.util.Calendar;
import java.util.Date;

public class BizUtils {

	public static String calUsefulTime(Date publishDate, Integer dualTime) {

		int dtime = dualTime == null ? 0 : dualTime;
		String time = "";

		Calendar createtime = Calendar.getInstance();
		createtime.setTime(publishDate);
		createtime.add(Calendar.MINUTE, dtime);

		Calendar currenttime = Calendar.getInstance();

		if (createtime.before(currenttime)) {
			time = "已过期";
		} else {

			long lasttime = createtime.getTimeInMillis() - currenttime.getTimeInMillis();
			int lastmin = (int) lasttime / (1000 * 60);
			time = lastmin + "分后过期";
		}

		return time;
	}

}
