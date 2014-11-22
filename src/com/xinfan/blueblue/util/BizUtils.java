package com.xinfan.blueblue.util;

import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.xinfan.blueblue.activity.common.DisplayImageOptionsConfig;
import com.xinfan.blueblue.activity.context.SystemConfigContext;

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

	public static String getHttpAvatarUrl(Context context, String name) {
		String ip = SystemConfigContext.getAddress(context);
		String http = "http://" + ip + "/file/avatar/" + name;
		return http;
	}

	public static void showAvatar(Context context, ImageView view, String name) {
		if (name != null && name.length() > 0) {
			String http = BizUtils.getHttpAvatarUrl(context, name);
			ImageLoader.getInstance().displayImage(http, view, DisplayImageOptionsConfig.avatar_options);
		}
	}

}
