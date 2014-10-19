package com.xinfan.blueblue.activity.context;

import android.content.Context;

import com.xinfan.blueblue.request.Constants;
import com.xinfan.blueblue.request.SharePreferenceUtil;

public class SystemConfigContext {

	public static void setAddress(Context context, String address) {
		SharePreferenceUtil util = new SharePreferenceUtil(context, Constants.USER_INFO);
		util.setAddress(address);
	}

	public static String getAddress(Context context) {
		SharePreferenceUtil util = new SharePreferenceUtil(context, Constants.USER_INFO);
		return util.getAddress();
	}

	public static void init(Context context) {

		String address = getAddress(context);
		if (address == null || address.length() == 0) {
			setAddress(context, Constants.http.http_request_ip);
		}

	}

}
