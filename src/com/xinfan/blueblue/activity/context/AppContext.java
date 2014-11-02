package com.xinfan.blueblue.activity.context;

import android.content.Context;

import com.igexin.sdk.PushManager;
import com.xinfan.blueblue.activity.base.BaseActivity;
import com.xinfan.blueblue.dao.DBHelper;

public class AppContext {

	public static void destroy(Context context) {
		for (BaseActivity act : BaseActivity.activitys) {
			act.finish();
		}

		// 关闭数据库
		DBHelper.getInstance().close();
	}

	public static void init(Context context) {

		// 初始数据库
		DBHelper.init(context);

		PushManager.getInstance().initialize(context.getApplicationContext());
	}

}
