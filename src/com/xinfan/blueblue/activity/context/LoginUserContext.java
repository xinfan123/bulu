package com.xinfan.blueblue.activity.context;

import android.content.Context;

import com.xinfan.blueblue.request.Constants;
import com.xinfan.blueblue.request.SharePreferenceUtil;

public class LoginUserContext {

	public static Long getUserId(Context context) {
		SharePreferenceUtil util = new SharePreferenceUtil(context, Constants.USER_INFO);
		return util.getUserId();
	}

}
