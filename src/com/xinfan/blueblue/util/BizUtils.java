package com.xinfan.blueblue.util;

import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.xinfan.blueblue.activity.common.DisplayImageOptionsConfig;
import com.xinfan.blueblue.activity.context.SystemConfigContext;
import com.xinfan.blueblue.runtime.GpsService;

public class BizUtils {
	
	public static void main(String[] args){
		System.out.println(calUsefulTime(new Date(),10));
	}

	public static String[] calUsefulTime(Date publishDate, Integer dualTime) {

		int dtime = dualTime == null ? 0 : dualTime;
		String times[] = new String[2];

		Calendar createtime = Calendar.getInstance();
		createtime.setTime(publishDate);
		createtime.add(Calendar.MINUTE, dtime);

		Calendar currenttime = Calendar.getInstance();

		if (createtime.before(currenttime)) {
			times[1] = "已过期";
			times[0] = "1";
		} else {

			long lasttime = createtime.getTimeInMillis() - currenttime.getTimeInMillis();
			int lastmin = (int) lasttime / (1000 * 60);
			
			int lasthour = new Double(Math.floor(lastmin/60)).intValue();
			lastmin = new Double(Math.floor(lastmin%60)).intValue();
			
			times[1] = lasthour +"时"+lastmin + "分";
			times[0] = "0";
		}

		return times;
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

	public static String calGps2mToString(String lat_a_str, String lng_a_str) {
		double s = 0;
		try {
			double lat_b = GpsService.last_gpsx;
			double lng_b = GpsService.last_gpsy;
			double lat_a = Double.parseDouble(lat_a_str);
			double lng_a = Double.parseDouble(lng_a_str);

			double radLat1 = (lat_a * Math.PI / 180.0);
			double radLat2 = (lat_b * Math.PI / 180.0);
			double a = radLat1 - radLat2;
			double b = (lng_a - lng_b) * Math.PI / 180.0;
			s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
			s = s * GpsService.EARTH_RADIUS;
			s = Math.round(s * 10000) / 10000;

		} catch (Exception e) {
			e.printStackTrace();
		}

		String mString = String.valueOf(Math.round((s / 1000)))+"km";

		return mString;
	}

}
