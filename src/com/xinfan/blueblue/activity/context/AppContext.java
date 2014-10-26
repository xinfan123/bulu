package com.xinfan.blueblue.activity.context;

import com.xinfan.blueblue.activity.base.BaseActivity;

public class AppContext {

	public static void destroy() {
		for (BaseActivity act : BaseActivity.activitys) {
			act.finish();
		}
	}

}
