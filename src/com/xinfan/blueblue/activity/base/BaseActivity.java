package com.xinfan.blueblue.activity.base;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;

public class BaseActivity extends Activity {

	public static List<BaseActivity> activitys = new ArrayList<BaseActivity>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		activitys.add(this);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		activitys.remove(this);
	}

}
