package com.xinfan.blueblue.activity.context;

import android.content.Context;

import com.xinfan.blueblue.request.Constants;
import com.xinfan.blueblue.request.SharePreferenceUtil;

public class LoginUserContext {

	public static Long getUserId(Context context) {
		SharePreferenceUtil util = new SharePreferenceUtil(context, Constants.USER_INFO);
		return util.getUserId();
	}

	public static String getMobile(Context context) {
		SharePreferenceUtil util = new SharePreferenceUtil(context, Constants.USER_INFO);
		return util.getMobile();
	}

	public static String getPassword(Context context) {
		SharePreferenceUtil util = new SharePreferenceUtil(context, Constants.USER_INFO);
		return util.getPasswd();
	}

	public static boolean getIsLogin(Context context) {
		SharePreferenceUtil util = new SharePreferenceUtil(context, Constants.USER_INFO);
		return util.getIsLogin();
	}

	public static void setIsLogin(Context context, boolean login) {
		SharePreferenceUtil util = new SharePreferenceUtil(context, Constants.USER_INFO);
		util.setIsLogin(login);
	}

	public static String getCID(Context context) {
		SharePreferenceUtil util = new SharePreferenceUtil(context, Constants.USER_INFO);
		return util.getCID();
	}

	public static void setCID(Context context, String cid) {
		SharePreferenceUtil util = new SharePreferenceUtil(context, Constants.USER_INFO);
		util.setCID(cid);
	}
	
	public static void setAvatar(Context context, String avatar) {
		SharePreferenceUtil util = new SharePreferenceUtil(context, Constants.USER_INFO);
		util.setAvatar(avatar);
	}
}
