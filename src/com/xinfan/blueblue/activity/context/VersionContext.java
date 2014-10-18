package com.xinfan.blueblue.activity.context;

import android.content.Context;

import com.xinfan.blueblue.request.Constants;
import com.xinfan.blueblue.request.SharePreferenceUtil;

public class VersionContext {

	public static void setVersion(Context context,String version) {
		SharePreferenceUtil util = new SharePreferenceUtil(context, Constants.USER_INFO);
		util.setVersion(version);
	}
	
	public static String getVersion(Context context) {
		SharePreferenceUtil util = new SharePreferenceUtil(context, Constants.USER_INFO);
		return util.getVersion();
	}
	

}
